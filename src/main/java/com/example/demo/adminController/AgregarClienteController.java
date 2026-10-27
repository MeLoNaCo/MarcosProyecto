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

    public AgregarClienteController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
                          @RequestParam(defaultValue = "false") boolean activo,
                          Model model) {
        String error = usuarioService.registrar(nombre, email, password, password, telefono, activo);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("nombre", nombre);
            model.addAttribute("email", email);
            model.addAttribute("telefono", telefono);
            return "admin/cliente-form";
        }
        return "redirect:/admin/clientes";
    }
}
