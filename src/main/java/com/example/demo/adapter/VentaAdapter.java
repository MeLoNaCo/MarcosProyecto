package com.example.demo.adapter;


import com.example.demo.entity.DetalleVentaEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.entity.VentaEntity;
import com.example.demo.model.Venta;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VentaAdapter {

    private final DetalleVentaAdapter detalleAdapter;

    public VentaAdapter(DetalleVentaAdapter detalleAdapter) {
        this.detalleAdapter = detalleAdapter;
    }

    public Venta toModel(VentaEntity entity) {
        if (entity == null) {
            return null;
        }

        Venta venta = new Venta();
        venta.setId(entity.getId());
        venta.setFecha(entity.getFecha());
        venta.setIdUsuario(entity.getUsuario().getId());
        venta.setNombreComprador(entity.getNombreComprador());
        venta.setDireccion(entity.getDireccion());
        venta.setEstado(entity.getEstado());
        venta.setDetalles(
                entity.getDetalles().stream()
                        .map(detalleAdapter::toModel)
                        .collect(Collectors.toList())
        );
        venta.setIdUsuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null);
        // El total no se setea: Venta lo calcula desde sus detalles
        return venta;
    }

    public VentaEntity toEntity(Venta model) {
        if (model == null) {
            return null;
        }

        VentaEntity entity = new VentaEntity();
        entity.setId(model.getId());
        entity.setFecha(model.getFecha());
        entity.setNombreComprador(model.getNombreComprador());
        entity.setDireccion(model.getDireccion());
        entity.setEstado(model.getEstado());
        entity.setTotal(model.getTotal());

        List<DetalleVentaEntity> detalles = model.getDetalles().stream()
                .map(detalleAdapter::toEntity)
                .collect(Collectors.toList());
        detalles.forEach(d -> d.setVenta(entity));
        entity.setDetalles(detalles);

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(model.getIdUsuario());
        entity.setUsuario(usuario);

        // El usuario (UsuarioEntity) lo asigna el service con el repository
        return entity;
    }
}