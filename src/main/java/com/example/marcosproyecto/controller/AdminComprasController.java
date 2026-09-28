package com.example.marcosproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// TODO Avance 2: ver detalle de cada compra en /admin/compras/{id}.
@Controller
public class AdminComprasController {

    @GetMapping("/admin/compras")
    public String compras(Model model) {
        return "admin/compras";
    }
}
