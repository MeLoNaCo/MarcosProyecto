package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.model.Categoria;
import com.example.marcosproyecto.model.Producto;
import com.example.marcosproyecto.service.CategoriaService;
import com.example.marcosproyecto.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO Avance 2: rutas /catalogo y /producto-detalle. Carrito y pago en CarritoController.
@Controller
public class CatalogoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public CatalogoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/catalogo")
    public String catalogo(@RequestParam(required = false) String categoria, Model model) {
        model.addAttribute("productos", productoService.listarPorCategoria(categoria));
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("categoriaActiva", categoria);

        return "tienda/catalogo";
    }
}
