package com.example.demo.service;

import com.example.demo.adapter.ProductoAdapter;
import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.model.Categoria;
import com.example.demo.model.Producto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private ProductoRepository productoRepository;
    private CategoriaRepository categoriaRepository;
    private ProductoAdapter productoAdapter;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, ProductoAdapter productoAdapter) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoAdapter = productoAdapter;
    }

    public List<Producto> listarProductos(){
        List<ProductoEntity> productos = productoRepository.findAll();

        return productos.stream()
                .map(e -> productoAdapter.toModel(e) )
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Producto> listarProductosActivos() {
        return productoRepository.findByActivoTrueAndStockGreaterThan(0).stream()
                .map(productoAdapter::toModel)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Producto> listarPorCategoria(String nombreCategoria) {
        if (!categoriaRepository.existsByNombre(nombreCategoria)) {
            throw new RuntimeException("Categoría no encontrada con nombre: " + nombreCategoria);
        }
        return productoRepository
                .findByCategoriaNombreAndActivoTrueAndStockGreaterThan(nombreCategoria, 0).stream()
                .map(productoAdapter::toModel)
                .collect(Collectors.toList());
    }

    public Producto buscarPorId(Long id) {
        // 1. Buscamos la entidad en el repositorio (lo que devuelve la BD)
        ProductoEntity entity = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        // 2. La convertimos al modelo de dominio y la retornamos
        return productoAdapter.toModel(entity);
    }

    @Transactional
    public String guardar(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            return "El nombre es obligatorio";
        }
        if (producto.getPrecio() <= 0) {
            return "El precio debe ser mayor a 0";
        }
        if (producto.getStock() < 0) {
            return "El stock no puede ser negativo";
        }
        if (producto.getIdCategoria() == null) {
            return "Debes elegir una categoría";
        }

        CategoriaEntity categoria = categoriaRepository.findById(producto.getIdCategoria())
                .orElse(null);
        if (categoria == null) {
            return "La categoría no existe";
        }

        ProductoEntity entity = productoAdapter.toEntity(producto, categoria);
        productoRepository.save(entity);
        return null;
    }


    public Producto desactivarProducto(Long id){
        ProductoEntity entity = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado con ID: "+id));

        entity.setActivo(false);

        ProductoEntity guardado = productoRepository.save(entity);
        return productoAdapter.toModel(guardado);
    }

    public Producto activarProducto(Long id){
        ProductoEntity entity = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado con ID: "+id));

        entity.setActivo(true);

        ProductoEntity guardado = productoRepository.save(entity);
        return productoAdapter.toModel(guardado);
    }
}
