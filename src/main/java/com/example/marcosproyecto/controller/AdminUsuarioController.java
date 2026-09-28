package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.service.CategoriaService;
import com.example.marcosproyecto.service.ProductoService;
import com.example.marcosproyecto.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// TODO Avance 2: CRUD /admin/clientes (usuarios).
@Controller
public class AdminUsuarioController {

    private final UsuarioService usuarioService;

    public AdminUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/admin/clientes")
    public String clientes(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "admin/clientes";
    }
}
