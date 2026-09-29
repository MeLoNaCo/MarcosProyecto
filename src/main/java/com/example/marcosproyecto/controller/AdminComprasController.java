package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Todas las compras del sistema con su detalle.
@Controller
public class AdminComprasController {

    private final VentaService ventaService;

    public AdminComprasController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping("/admin/compras")
    public String compras(Model model) {
        model.addAttribute("ventas", ventaService.listarTodas());
        return "admin/compras";
    }
}
