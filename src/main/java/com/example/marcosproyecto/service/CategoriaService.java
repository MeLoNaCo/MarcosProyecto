package com.example.marcosproyecto.service;

import com.example.marcosproyecto.model.Categoria;
import com.example.marcosproyecto.model.Producto;
import com.example.marcosproyecto.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// TODO Avance 2: reglas de negocio de categoría (ej. no eliminar con productos, solo desactivar).
@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    public List<Categoria> listar() {
        List<Categoria> resultado = new ArrayList<>();

        for (Categoria c : categoriaRepository.findAll()) {
            if (c.isActivo()) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        try {
            return categoriaRepository.findById(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // Crea o actualiza. Devuelve error o null si todo ok.
    public String guardar(Categoria categoria) {
        if (categoria.getNombre() == null || categoria.getNombre().isBlank()) {
            return "La categoría necesita un nombre";
        }
        if (categoria.getId() == null) {
            categoria.setActivo(true);
            categoriaRepository.save(categoria);
            return null;
        }
        Categoria actual = buscarPorId(categoria.getId());
        if (actual == null) {
            return "Categoría no encontrada";
        }
        actual.setNombre(categoria.getNombre());
        actual.setDescripcion(categoria.getDescripcion());
        actual.setDescuentoPct(categoria.getDescuentoPct());
        actual.setActivo(categoria.isActivo());
        return null;
    }

    // Regla: no se elimina si tiene productos, solo se desactiva.
    public String eliminar(Long id, boolean tieneProductos) {
        if (tieneProductos) {
            Categoria actual = buscarPorId(id);
            if (actual != null) {
                actual.setActivo(false);
            }
            return "Tiene productos: se desactivó en vez de eliminar";
        }
        categoriaRepository.deleteById(id);
        return null;
    }
}
