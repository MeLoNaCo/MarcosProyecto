package com.example.demo.adapter;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaAdapter {

    public CategoriaEntity toEntity(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(categoria.getId());
        entity.setNombre(categoria.getNombre());
        entity.setActivo(categoria.isActivo());
        return entity;
    }

    public Categoria toModel(CategoriaEntity entity) {
        if (entity == null) {
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setId(entity.getId());
        categoria.setNombre(entity.getNombre());
        categoria.setActivo(entity.isActivo());
        return categoria;
    }
}