package com.example.demo.adapter;

import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAdapter {

    public Usuario toModel(UsuarioEntity entity){
        if (entity == null) {
            return null;
        }
        Usuario usuario = new Usuario();

        usuario.setId(entity.getId());
        usuario.setNombre(entity.getNombre());
        usuario.setEmail(entity.getEmail());
        usuario.setPassword(entity.getEmail());
        usuario.setTelefono(entity.getTelefono());
        usuario.setRol(entity.getRol());
        usuario.setActivo(entity.isActivo());
        usuario.setVentas(entity.getVentas());

        return usuario;
    }

    public UsuarioEntity toEntity(Usuario model){
        if (model == null) {
            return null;
        }
        UsuarioEntity entity = new UsuarioEntity();

        entity.setId(model.getId());
        entity.setNombre(model.getNombre());
        entity.setEmail(model.getEmail());
        entity.setPassword(model.getEmail());
        entity.setTelefono(model.getTelefono());
        entity.setRol(model.getRol());
        entity.setActivo(model.isActivo());
        entity.setVentas(model.getVentas());

        return entity;
    }
}
