package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.model.Categoria;
import com.example.marcosproyecto.service.CategoriaService;
import com.example.marcosproyecto.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// CRUD de categorías (Anexo 3: entidad categoría obligatoria).
@Controller
public class AdminCategoriaController {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;

    public AdminCategoriaController(CategoriaService categoriaService, ProductoService productoService) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
    }

    @GetMapping("/admin/categorias")
    public String listar(Model model) {
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/categorias";
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
            return "admin/categoria-form";
        }
        return "redirect:/admin/categorias";
    }

    @GetMapping("/admin/categorias/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Categoria c = categoriaService.buscarPorId(id);
        if (c == null) {
            return "redirect:/admin/categorias";
        }
        model.addAttribute("categoria", c);
        return "admin/categoria-form";
    }

    @PostMapping("/admin/categorias/eliminar")
    public String eliminar(@RequestParam Long id, Model model) {
        String aviso = categoriaService.eliminar(id, productoService.contarPorCategoriaId(id) > 0);
        if (aviso != null) {
            model.addAttribute("aviso", aviso);
        }
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/categorias";
    }
}
