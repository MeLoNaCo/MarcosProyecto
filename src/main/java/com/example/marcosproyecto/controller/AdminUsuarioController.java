package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/admin/clientes/nuevo")
    public String nuevo() {
        return "admin/cliente-form";
    }

    @PostMapping("/admin/clientes/guardar")
    public String guardar(@RequestParam String nombre,
                          @RequestParam String apellidos,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam(required = false) String telefono,
                          Model model) {
        String error = usuarioService.registrar(nombre, apellidos, email, password, password);
        if (error != null) {
            model.addAttribute("error", error);
            return "admin/cliente-form";
        }
        return "redirect:/admin/clientes";
    }
}
