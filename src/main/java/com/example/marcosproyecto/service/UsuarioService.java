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

    // Actualiza datos del perfil. Devuelve mensaje de error o null si todo ok.
    public String actualizar(Long id, String nombre, String apellidos, String email, String telefono) {
        for (Usuario o : usuarioRepository.findAll()) {
            if (!o.getId().equals(id) && o.getEmail().equalsIgnoreCase(email)) {
                return "Ese correo ya está en uso por otra cuenta";
            }
        }
        if (telefono != null && !telefono.isBlank() && !telefono.matches("\\+?[0-9 ]{7,15}")) {
            return "Teléfono inválido: usa solo números, + inicial opcional, entre 7 y 15 dígitos";
        }
        Usuario u = usuarioRepository.findById(id);
        if (u == null) {
            return "Usuario no encontrado";
        }
        u.setNombre(nombre);
        u.setApellidos(apellidos);
        u.setEmail(email);
        u.setTelefono(telefono);
        return null;
    }

    // Registra un CLIENTE nuevo. Devuelve mensaje de error o null si todo ok.
    public String registrar(String nombre, String apellidos, String email, String password, String confirm) {
        if (nombre == null || nombre.isBlank() || apellidos == null || apellidos.isBlank()) {
            return "Completa tu nombre y apellidos";
        }
        if (usuarioRepository.findByEmail(email) != null) {
            return "Ese correo ya tiene una cuenta";
        }
        if (password == null || password.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres";
        }
        if (!password.equals(confirm)) {
            return "Las contraseñas no coinciden";
        }
        Usuario nuevo = new Usuario(null, nombre, apellidos, email, password, "", "CLIENTE");
        usuarioRepository.save(nuevo);
        return null;
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
