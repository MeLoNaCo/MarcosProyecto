package com.example.marcosproyecto.repository;

import com.example.marcosproyecto.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private final List<Usuario> usuarios = new ArrayList<>();
    private long siguienteId = 4;

    public UsuarioRepository() {
        usuarios.add(new Usuario(1L, "Admin", "HeroGames", "admin@herogames.com",
                "admin123", "999888777", "ADMIN"));
        usuarios.add(new Usuario(2L, "Leonardo", "Garay", "cliente1@herogames.com",
                "cliente123", "999111222", "CLIENTE"));
        usuarios.add(new Usuario(3L, "Maria", "Torres", "cliente2@herogames.com",
                "cliente123", "999333444", "CLIENTE"));
        usuarios.add(new Usuario(4L, "Admin", "HeroGames", "123",
                "123", "999888777", "ADMIN"));
    }

    public List<Usuario> findAll() {
        return usuarios;
    }

    public Usuario findByEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public Usuario findById(Long id) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(siguienteId);
            siguienteId = siguienteId + 1;
        }
        usuarios.add(usuario);
        return usuario;
    }
}
