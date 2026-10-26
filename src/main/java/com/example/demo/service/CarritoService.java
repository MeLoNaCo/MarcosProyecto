package com.example.demo.service;

import com.example.demo.adapter.ProductoAdapter;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.model.DetalleVenta;
import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CarritoService {
    private final ProductoRepository productoRepository;
    private final List<DetalleVenta> items = new ArrayList<>();
    private final ProductoAdapter productoAdapter;

    public CarritoService(ProductoRepository productoRepository, ProductoAdapter productoAdapter) {
        this.productoRepository = productoRepository;
        this.productoAdapter = productoAdapter;
    }

    public void agregarCarrito(Long productoId){
        for (DetalleVenta d : items) {
            if (d.getProducto().getId().equals(productoId)) {
                d.setCantidad(d.getCantidad() + 1);
                return;
            }
        }
        ProductoEntity pEntity = productoRepository.findById(productoId).orElse(null);
        Producto p = productoAdapter.toModel(pEntity);

        if (p != null && p.isActivo()) {
            items.add(new DetalleVenta(p, 1));
        }
    }

    public List<DetalleVenta> listar() {
        return items;
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
