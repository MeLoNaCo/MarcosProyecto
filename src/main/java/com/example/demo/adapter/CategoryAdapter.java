package com.example.demo.adapter;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.model.Categoria;

public class CategoryAdapter {

    public CategoriaEntity toEntity(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(categoria.getId());
        categoriaEntity.setNombre(categoria.getNombre());
        return categoriaEntity;
    }



    public Categoria toModel(CategoriaEntity categoriaEntity) {
        if (categoriaEntity == null) {
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setId(categoriaEntity.getId());
        categoria.setNombre(categoriaEntity.getNombre());
        return categoria;
    }

}
