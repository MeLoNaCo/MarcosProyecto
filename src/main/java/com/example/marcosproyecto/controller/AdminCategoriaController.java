package com.example.marcosproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// TODO Avance 2: CRUD /admin/categorias (Anexo 3, entidad categoría obligatoria).
@Controller
public class AdminCategoriaController {

    @GetMapping("/admin/categorias")
    public String categorias(Model model) {
        return "admin/categorias";
    }
}
