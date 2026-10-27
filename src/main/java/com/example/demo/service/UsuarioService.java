package com.example.demo.service;

import com.example.demo.adapter.UsuarioAdapter;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioAdapter usuarioAdapter;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioAdapter usuarioAdapter) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioAdapter = usuarioAdapter;
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuarioAdapter::toModel)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioAdapter::toModel)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public Usuario validar(String email, String password) {
        List<UsuarioEntity> encontrados = usuarioRepository.findByEmail(email);
        if (encontrados.isEmpty()) {
            return null;
        }
        UsuarioEntity entity = encontrados.get(0);
        if (!entity.isActivo() || !entity.getPassword().equals(password)) {
            return null;
        }
        return usuarioAdapter.toModel(entity);
    }

    @Transactional
    public String registrar(String nombre, String email, String password, String confirm,
                            String telefono, boolean activo) {
        if (nombre == null || nombre.isBlank()) {
            return "Completa tu nombre";
        }
        if (!usuarioRepository.findByEmail(email).isEmpty()) {
            return "Ese correo ya tiene una cuenta";
        }
        if (password == null || password.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres";
        }
        if (!password.equals(confirm)) {
            return "Las contraseñas no coinciden";
        }
        if (telefono != null && !telefono.isBlank() && !telefono.matches("\\+?[0-9 ]{7,15}")) {
            return "Teléfono inválido: usa solo números, + inicial opcional, entre 7 y 15 dígitos";
        }

        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setEmail(email);
        nuevo.setPassword(password);
        nuevo.setTelefono(telefono);
        nuevo.setActivo(activo);

        usuarioRepository.save(usuarioAdapter.toEntity(nuevo));
        return null;
    }

    public String registrar(String nombre, String email, String password, String confirm) {
        return registrar(nombre, email, password, confirm, null, true);
    }

    @Transactional
    public String actualizar(Long id, String nombre, String email, String telefono, boolean activo) {
        if (nombre == null || nombre.isBlank()) {
            return "Completa tu nombre";
        }
        if (telefono != null && !telefono.isBlank() && !telefono.matches("\\+?[0-9 ]{7,15}")) {
            return "Teléfono inválido: usa solo números, + inicial opcional, entre 7 y 15 dígitos";
        }

        UsuarioEntity entity = usuarioRepository.findById(id).orElse(null);
        if (entity == null) {
            return "Usuario no encontrado";
        }

        List<UsuarioEntity> otros = usuarioRepository.findByEmail(email);
        if (!otros.isEmpty() && !otros.get(0).getId().equals(id)) {
            return "Ese correo ya está en uso por otra cuenta";
        }

        entity.setNombre(nombre);
        entity.setEmail(email);
        entity.setTelefono(telefono);
        entity.setActivo(activo);
        usuarioRepository.save(entity);
        return null;
    }

    public long contarUsuarios() {
        return usuarioRepository.count();
    }
}
