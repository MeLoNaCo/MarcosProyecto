package com.example.demo.adminController;

import com.example.demo.adapter.CategoriaAdapter;
import com.example.demo.model.Categoria;
import com.example.demo.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaAdapter categoriaAdapter;

    public CategoriaController(CategoriaService categoriaService, CategoriaAdapter categoriaAdapter) {
        this.categoriaService = categoriaService;
        this.categoriaAdapter = categoriaAdapter;
    }

    @GetMapping({"/categorias","/admin/categorias"})
    public String categoria(Model model){

        model.addAttribute("categorias",categoriaService.listarCategorias());
        return "admin/categorias";
    }

    @GetMapping("/admin/categorias/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("categoria", categoriaService.buscarPorId(id));
        return "admin/categoria-form";
    }
}