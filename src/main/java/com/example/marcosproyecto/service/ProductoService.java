package com.example.marcosproyecto.service;

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
}
