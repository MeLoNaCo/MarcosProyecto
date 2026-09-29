package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.model.Producto;
import com.example.marcosproyecto.service.CategoriaService;
import com.example.marcosproyecto.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// CRUD de productos (Anexo 3).
@Controller
public class AdminProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public AdminProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/admin/productos")
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarTodas());
        return "admin/productos";
    }

    @GetMapping("/admin/productos/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/producto-form";
    }

    @PostMapping("/admin/productos/guardar")
    public String guardar(@ModelAttribute Producto producto, Model model) {
        String error = productoService.guardar(producto);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "admin/producto-form";
        }
        return "redirect:/admin/productos";
    }

    @GetMapping("/admin/productos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto p = productoService.buscarPorId(id);
        if (p == null) {
            return "redirect:/admin/productos";
        }
        model.addAttribute("producto", p);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "admin/producto-form";
    }

    @PostMapping("/admin/productos/eliminar")
    public String eliminar(Long id) {
        productoService.eliminar(id);
        return "redirect:/admin/productos";
    }
}
