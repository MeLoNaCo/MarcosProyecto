package com.example.marcosproyecto.service;

import com.example.marcosproyecto.model.DetalleVenta;
import com.example.marcosproyecto.model.Producto;
import com.example.marcosproyecto.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Reglas de negocio de producto: solo activos a la venta (desactivar bloquea venta).
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listar() {
        return listarPorCategoria(null);
    }

    public List<Producto> listarPorCategoria(String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productoRepository.findAll()) {
            if (!p.isActivo()) {
                continue;
            }
            if (categoria == null || categoria.isBlank()) {
                resultado.add(p);
            } else if (p.getCategoria() != null
                    && p.getCategoria().getNombre().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Producto> listarOfertas() {
        List<Producto> conOferta = new ArrayList<>();
        for (Producto p : productoRepository.findAll()) {
            if (p.isActivo() && p.getOferta() > 0) {
                conOferta.add(p);
            }
        }
        for (int i = 0; i < conOferta.size(); i++) {
            for (int j = i + 1; j < conOferta.size(); j++) {
                if (conOferta.get(j).getOferta() > conOferta.get(i).getOferta()) {
                    Producto tmp = conOferta.get(i);
                    conOferta.set(i, conOferta.get(j));
                    conOferta.set(j, tmp);
                }
            }
        }
        List<Producto> top = new ArrayList<>();
        for (int i = 0; i < conOferta.size() && i < 8; i++) {
            top.add(conOferta.get(i));
        }
        return top;
    }

    // Recomienda productos de las mismas categorías del carrito,
    // sin repetir los que ya están dentro. Máximo 4.
    public List<Producto> recomendar(List<DetalleVenta> items) {
        List<String> cats = new ArrayList<>();
        for (DetalleVenta d : items) {
            String n = d.getProducto().getCategoria().getNombre();
            boolean existe = false;
            for (String c : cats) {
                if (c.equalsIgnoreCase(n)) {
                    existe = true;
                }
            }
            if (!existe) {
                cats.add(n);
            }
        }
        List<Producto> rec = new ArrayList<>();
        for (Producto p : productoRepository.findAll()) {
            if (!p.isActivo()) {
                continue;
            }
            boolean enCarrito = false;
            for (DetalleVenta d : items) {
                if (d.getProducto().getId().equals(p.getId())) {
                    enCarrito = true;
                }
            }
            if (enCarrito) {
                continue;
            }
            for (String c : cats) {
                if (p.getCategoria() != null && p.getCategoria().getNombre().equalsIgnoreCase(c)) {
                    rec.add(p);
                    break;
                }
            }
            if (rec.size() >= 4) {
                break;
            }
        }
        return rec;
    }
}
