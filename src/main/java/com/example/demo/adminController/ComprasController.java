package com.example.demo.adminController;

import com.example.demo.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComprasController {

    private final VentaService ventaService;

    public ComprasController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping({"/compras","/admin/compras"})
    public String compras(Model model){
            model.addAttribute("ventas", ventaService.listarVentas());
        return "admin/compras";
    }
}
