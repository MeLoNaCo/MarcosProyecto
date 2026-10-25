package com.example.demo.adminController;

import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientesController {

    private final UsuarioService usuarioService;

    public ClientesController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/clientes","/admin/clientes"})
    public String clientes(Model model){
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "admin/clientes";
    }
}
