package com.example.demo.adminController;

import com.example.demo.adapter.ProductoAdapter;
import com.example.demo.model.Producto;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/admin/productos")
    public String productos(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "admin/productos";
    }

    @GetMapping("/admin/productos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto p = productoService.buscarPorId(id);
        if (p == null) {
            return "redirect:/admin/productos";
        }
        model.addAttribute("producto", p);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "admin/producto-form";
    }
}