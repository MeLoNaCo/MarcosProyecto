package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VentaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;
    private final VentaService ventaService;

    public AuthController(UsuarioService usuarioService, VentaService ventaService) {
        this.usuarioService = usuarioService;
        this.ventaService = ventaService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/registro")
    public String registro() {
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String doRegistro(@RequestParam String nombre,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String confirm,
                             HttpServletRequest request,
                             Model model) {
        String error = usuarioService.registrar(nombre, email, password, confirm);
        if (error != null) {
            model.addAttribute("error", error);
            return "auth/registro";
        }
        try {
            request.login(email, password);
        } catch (ServletException e) {
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @GetMapping("/cuenta")
    public String cuenta(Authentication auth, Model model) {
        Usuario usuario = usuarioService.buscarPorEmail(auth.getName());
        model.addAttribute("usuario", usuario);
        model.addAttribute("misPedidos", ventaService.listarPorUsuario(usuario.getId()));
        return "auth/cuenta";
    }

    @PostMapping("/cuenta/perfil")
    public String guardarPerfil(@RequestParam String nombre,
                                @RequestParam String email,
                                @RequestParam(required = false) String telefono,
                                Authentication auth,
                                Model model,
                                RedirectAttributes redirect) {
        Usuario usuario = usuarioService.buscarPorEmail(auth.getName());
        String error = usuarioService.actualizar(usuario.getId(), nombre, email, telefono, usuario.isActivo());
        if (error != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("errorPerfil", error);
            model.addAttribute("misPedidos", ventaService.listarPorUsuario(usuario.getId()));
            return "auth/cuenta";
        }
        redirect.addFlashAttribute("okPerfil", "Cambios guardados");
        return "redirect:/cuenta";
    }
}