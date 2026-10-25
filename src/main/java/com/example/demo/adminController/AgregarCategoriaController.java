package com.example.demo.adminController;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgregarCategoriaController {

    private final CategoriaService categoriaService;

    public AgregarCategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/admin/categorias/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "admin/categoria-form";
    }

    @PostMapping("/admin/categorias/guardar")
    public String guardar(@ModelAttribute Categoria categoria, Model model) {
        String error = categoriaService.guardar(categoria);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("categoria", categoria);
            return "admin/categoria-form";
        }
        return "redirect:/admin/categorias";
    }
}