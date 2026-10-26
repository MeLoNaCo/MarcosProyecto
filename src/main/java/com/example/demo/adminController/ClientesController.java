package com.example.demo.adminController;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/admin/clientes/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            return "redirect:/admin/clientes";
        }
        model.addAttribute("usuario", usuario);
        return "admin/cliente-form";
    }

    @PostMapping("/admin/clientes/actualizar")
    public String actualizar(@RequestParam Long id,
                             @RequestParam String nombre,
                             @RequestParam String email,
                             @RequestParam(required = false) String telefono,
                             Model model) {
        String error = usuarioService.actualizar(id, nombre, email, telefono);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("usuario", usuarioService.buscarPorId(id));
            return "admin/cliente-form";
        }
        return "redirect:/admin/clientes";
    }
}
