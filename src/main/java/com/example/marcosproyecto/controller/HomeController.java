package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// TODO Avance 2: rutas /, /about, /contact, /publicidad (Anexo 2 estáticas).
@Controller
public class HomeController {

    private final ProductoService productoService;

    public HomeController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("ofertas", productoService.listarOfertas());
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
