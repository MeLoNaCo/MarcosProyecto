package com.example.demo.service;

import com.example.demo.adapter.UsuarioAdapter;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioAdapter usuarioAdapter;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository, UsuarioAdapter usuarioAdapter) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioAdapter = usuarioAdapter;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Admin
        if (usuarioRepository.findByEmail("admin@herogames.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setEmail("admin@herogames.com");
            admin.setPassword(encoder.encode("admin123"));
            admin.setTelefono("987456321");
            admin.setRol("ADMIN");
            admin.setActivo(true);
            usuarioRepository.save(usuarioAdapter.toEntity(admin));
        }

        // 1. Juan Pérez
        if (usuarioRepository.findByEmail("juan@email.com").isEmpty()) {
            Usuario juan = new Usuario();
            juan.setNombre("Juan Pérez");
            juan.setEmail("juan@email.com");
            juan.setPassword(encoder.encode("123456"));
            juan.setTelefono("987654321");
            juan.setRol("CLIENTE");
            juan.setActivo(true);
            usuarioRepository.save(usuarioAdapter.toEntity(juan));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity entity = usuarioRepository.findByEmail(email)
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        Usuario usuario = usuarioAdapter.toModel(entity);

        return new CustomUserDetails(
                usuario.getEmail(),
                usuario.getPassword(),
                List.of(usuario.getRol()),
                usuario.isActivo()
        );
    }
}