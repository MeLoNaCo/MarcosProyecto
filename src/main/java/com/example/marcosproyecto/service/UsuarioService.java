package com.example.marcosproyecto.service;

import com.example.marcosproyecto.model.Usuario;
import com.example.marcosproyecto.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Reglas de negocio de usuario: email único, solo activos entran, roles ADMIN/CLIENTE.
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar() {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : usuarioRepository.findAll()) {
            if (u.isActivo()) {
                resultado.add(u);
            }
        }
        return resultado;
    }

    // Devuelve el usuario si email+password coinciden y está activo; si no, null.
    public Usuario validar(String email, String password) {
        Usuario u = usuarioRepository.findByEmail(email);
        if (u == null) {
            return null;
        }
        if (!u.isActivo()) {
            return null;
        }
        if (!u.getPassword().equals(password)) {
            return null;
        }
        return u;
    }
}
