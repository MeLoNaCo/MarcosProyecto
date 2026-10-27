package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VentaService;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/registro")
    public String doRegistro(@RequestParam String nombre,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String confirm,
                             HttpSession session,
                             Model model) {
        String error = usuarioService.registrar(nombre, email, password, confirm);
        if (error != null) {
            model.addAttribute("error", error);
            return "auth/registro";
        }
        session.setAttribute("usuario", usuarioService.validar(email, password));
        return "redirect:/";
    }

    @GetMapping("/cuenta")
    public String cuenta(HttpSession session, Model model) {
        Object obj = session.getAttribute("usuario");
        if (!(obj instanceof Usuario usuario)) {
            return "redirect:/login";
        }
        model.addAttribute("misPedidos", ventaService.listarPorUsuario(usuario.getId()));
        return "auth/cuenta";
    }

    @PostMapping("/cuenta/perfil")
    public String guardarPerfil(@RequestParam String nombre,
                                @RequestParam String email,
                                @RequestParam(required = false) String telefono,
                                HttpSession session,
                                Model model,
                                RedirectAttributes redirect) {
        Object obj = session.getAttribute("usuario");
        if (!(obj instanceof Usuario usuario)) {
            return "redirect:/login";
        }
        String error = usuarioService.actualizar(usuario.getId(), nombre, email, telefono, usuario.isActivo());
        if (error != null) {
            model.addAttribute("errorPerfil", error);
            model.addAttribute("misPedidos", ventaService.listarPorUsuario(usuario.getId()));
            return "auth/cuenta";
        }
        session.setAttribute("usuario", usuarioService.buscarPorId(usuario.getId()));
        redirect.addFlashAttribute("okPerfil", "Cambios guardados");
        return "redirect:/cuenta";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}