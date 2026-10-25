package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public CatalogoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/catalogo"})
    public String catalogo(@RequestParam(required = false)String categoria, Model model){
        List<Producto> productos;

        if (categoria != null) {
            productos = productoService.listarPorCategoria(categoria);
        } else {
            productos = productoService.listarProductosActivos();
        }

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaService.listarCategoriasActivas());
        model.addAttribute("categoriaActiva", categoria);
        return "tienda/catalogo";
    }
}
