package com.example.demo.adminController;

import com.example.demo.model.Producto;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgregarProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public AgregarProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/admin/productos/nuevo")
    public String agregarProducto(Model model){
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "admin/producto-form";
    }

    @PostMapping("/admin/productos/guardar")
    public String guardar(@ModelAttribute Producto producto, Model model) {
        String error = productoService.guardar(producto);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("producto", producto);
            model.addAttribute("categorias", categoriaService.listarCategorias());
            return "admin/producto-form";
        }
        return "redirect:/admin/productos";
    }
}
