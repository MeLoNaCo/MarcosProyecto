package com.example.demo.service;

import com.example.demo.adapter.CategoriaAdapter;
import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.model.Categoria;
import com.example.demo.model.Producto;
import com.example.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaAdapter categoriaAdapter;


    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaAdapter categoriaAdapter) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaAdapter = categoriaAdapter;
    }

    public List<Categoria> listarCategorias(){
        List<CategoriaEntity> categorias = categoriaRepository.findAll();

        return categorias.stream()
                .map(e -> categoriaAdapter.toModel(e) )
                .collect(Collectors.toList());
    }

    public List<Categoria> listarCategoriasActivas(){
        List<CategoriaEntity> categorias = categoriaRepository.findByActivoTrue();

        return categorias.stream()
                .map(e -> categoriaAdapter.toModel(e) )
                .collect(Collectors.toList());
    }

    @Transactional
    public String guardar(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            return "El nombre es obligatorio";
        }

        CategoriaEntity entity = categoriaAdapter.toEntity(categoria);
        categoriaRepository.save(entity);
        return null;
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaAdapter::toModel)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada: " + id));
    }

}
