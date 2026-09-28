package com.example.marcosproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// TODO Avance 2: CRUD /admin/productos (lista, nuevo, editar, eliminar).
@Controller
public class AdminProductoController {

    @GetMapping("/admin/productos")
    public String productos(Model model) {
        return "admin/productos";
    }
}
