package com.example.demo.service;

import com.example.demo.adapter.UsuarioAdapter;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

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

    public List<Usuario> listarUsuarios(){
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();

        return usuarios.stream().map(e ->usuarioAdapter.toModel(e))
                .collect(Collectors.toList());
    }

    public long contarUsuarios(){
        return usuarioRepository.count();
    }
}
