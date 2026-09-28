package com.example.marcosproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// TODO Avance 2: rutas /, /about, /contact, /publicidad (Anexo 2 estáticas).
@Controller
public class HomeController {
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping({"/about"})
    public String about(Model model) {
        return "info/about";
    }

    @GetMapping({"/contact"})
    public String contact(Model model) {
        return "info/contact";
    }

    @GetMapping({"/publicidad"})
    public String publicidad(Model model) {
        return "info/publicidad";
    }
}
