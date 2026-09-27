package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.model.Usuario;
import com.example.marcosproyecto.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        Usuario u = usuarioService.validar(email, password);
        if (u == null) {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "auth/login";
        }
        session.setAttribute("usuario", u);
        return "redirect:/";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        return "auth/registro";
    }

    @GetMapping("/cuenta")
    public String cuenta(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return "auth/cuenta";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
