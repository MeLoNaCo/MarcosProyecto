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
}
