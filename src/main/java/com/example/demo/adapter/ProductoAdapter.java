package com.example.demo.adapter;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoAdapter {

    //Convertir ProductoEntity a model
    public Producto toModel(ProductoEntity entity){
        if (entity == null) {
            return null;
        }

        Producto producto = new Producto();
        producto.setId(entity.getId());
        producto.setNombre(entity.getNombre());
        producto.setDescripcion(entity.getDescripcion());
        producto.setPrecio(entity.getPrecio());
        producto.setStock(entity.getStock());
        producto.setImagenUrl(entity.getImagenUrl());
        producto.setOferta(entity.getOferta());
        producto.setActivo(entity.isActivo());
        producto.setNombreCategoria(entity.getCategoria() != null ? entity.getCategoria().getNombre() : "");
        producto.setIdCategoria(entity.getCategoria().getId());

        return producto;
    }

    //Convertir de Model a ProductoEntity
    public ProductoEntity toEntity(Producto model, CategoriaEntity categoria){
        if (model == null) {
            return null;
        }
        ProductoEntity entity = new ProductoEntity();
        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setDescripcion(model.getDescripcion());
        entity.setPrecio(model.getPrecio());
        entity.setStock(model.getStock());
        entity.setImagenUrl(model.getImagenUrl());
        entity.setOferta(model.getOferta());
        entity.setActivo(model.isActivo());

        entity.setCategoria(categoria);

        return entity;
    }

}
