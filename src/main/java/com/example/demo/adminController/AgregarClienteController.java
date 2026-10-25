package com.example.demo.adminController;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AgregarClienteController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public AgregarClienteController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/admin/clientes/nuevo")
    public String nuevo() {
        return "admin/cliente-form";
    }

    @PostMapping("/admin/clientes/guardar")
    public String guardar(@RequestParam String nombre,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam(required = false) String telefono,
                          Model model) {
        return "redirect:/admin/clientes";
    }
}
