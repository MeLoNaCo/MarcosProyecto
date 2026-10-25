package com.example.demo.adapter;

import com.example.demo.entity.DetalleVentaEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.model.DetalleVenta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaAdapter {

    private final ProductoAdapter productoAdapter;

    public DetalleVentaAdapter(ProductoAdapter productoAdapter) {
        this.productoAdapter = productoAdapter;
    }

    public DetalleVenta toModel(DetalleVentaEntity entity) {
        if (entity == null) {
            return null;
        }

        DetalleVenta detalle = new DetalleVenta();
        detalle.setId(entity.getId());
        detalle.setProducto(productoAdapter.toModel(entity.getProducto()));
        detalle.setCantidad(entity.getCantidad());
        detalle.setPrecioUnitario(entity.getPrecioUnitario());
        return detalle;
    }

    public DetalleVentaEntity toEntity(DetalleVenta model) {
        if (model == null) {
            return null;
        }

        DetalleVentaEntity entity = new DetalleVentaEntity();
        entity.setId(model.getId());
        entity.setCantidad(model.getCantidad());
        entity.setPrecioUnitario(model.getPrecioUnitario());

        if (model.getProducto() != null) {
            ProductoEntity producto = new ProductoEntity();
            producto.setId(model.getProducto().getId());
            entity.setProducto(producto);
        }

        return entity;
    }
}