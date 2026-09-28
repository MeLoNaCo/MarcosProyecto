package com.example.marcosproyecto.service;

import com.example.marcosproyecto.model.DetalleVenta;
import com.example.marcosproyecto.model.Producto;
import com.example.marcosproyecto.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

// Un carrito por navegador: Spring crea una instancia por sesión,
// así cada cliente solo ve lo que ÉL añadió.
@Service
@SessionScope
public class CarritoService {

    private final ProductoRepository productoRepository;
    private final List<DetalleVenta> items = new ArrayList<>();

    public CarritoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void agregar(Long productoId) {
        for (DetalleVenta d : items) {
            if (d.getProducto().getId().equals(productoId)) {
                d.setCantidad(d.getCantidad() + 1);
                return;
            }
        }
        Producto p = productoRepository.findById(productoId);
        if (p != null && p.isActivo()) {
            items.add(new DetalleVenta(p, 1));
        }
    }

    public void quitar(Long productoId) {
        DetalleVenta borrar = null;
        for (DetalleVenta d : items) {
            if (d.getProducto().getId().equals(productoId)) {
                borrar = d;
            }
        }
        if (borrar != null) {
            items.remove(borrar);
        }
    }

    public void cambiarCantidad(Long productoId, int cantidad) {
        for (DetalleVenta d : items) {
            if (d.getProducto().getId().equals(productoId)) {
                d.setCantidad(cantidad);
            }
        }
    }

    public List<DetalleVenta> listar() {
        return items;
    }

    public double total() {
        double suma = 0;
        for (DetalleVenta d : items) {
            suma = suma + d.getSubtotal();
        }
        return suma;
    }

    public int totalCantidad() {
        int n = 0;
        for (DetalleVenta d : items) {
            n = n + d.getCantidad();
        }
        return n;
    }

    public void vaciar() {
        items.clear();
    }
}
