package com.example.demo.adminController;

import com.example.demo.service.UsuarioService;
import com.example.demo.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final VentaService ventaService;
    private final UsuarioService usuarioService;

    public DashboardController(VentaService ventaService, UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/admin","/admin/dashboard"})
    public String dashboard(Model model){
        model.addAttribute("totalVentas", ventaService.contarVentas());
        model.addAttribute("totalUsuarios", usuarioService.contarUsuarios());
        model.addAttribute("totalIngresos",ventaService.contarIngresosTotales());

        return "admin/dashboard";
    }
}
