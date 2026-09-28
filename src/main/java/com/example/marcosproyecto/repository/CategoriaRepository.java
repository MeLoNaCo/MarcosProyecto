package com.example.marcosproyecto.repository;

import com.example.marcosproyecto.model.Categoria;
import com.example.marcosproyecto.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaRepository {

    private final List<Categoria> categoria = new ArrayList<>();
    public CategoriaRepository() {
        Categoria perifericos = new Categoria(1L,"Perifericos", 0);
        categoria.add(perifericos);
        Categoria laptops = new Categoria(2L,"Laptops", 0);
        categoria.add(laptops);
        Categoria monitores = new Categoria(3L,"Monitores", 0);
        categoria.add(monitores);
        Categoria juegos = new Categoria(4L,"Juegos", 0);
        categoria.add(juegos);
    }

    public List<Categoria> findAll() {
        return categoria;
    }

    public Categoria findById(Long id) {
        for (Categoria c : categoria) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Categoria no existe: " + id);
    }

}
