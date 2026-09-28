package com.example.marcosproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

// TODO Avance 2: métricas reales cuando tengas el módulo de reportes.
@Controller
public class AdminReportesController {

    @GetMapping("/admin/reportes")
    public String reportes(Model model) {
        model.addAttribute("reporte", Map.of(
                "ventasMes", 0.0,
                "ordenesTotales", 0,
                "clientesNuevos", 0,
                "ticketPromedio", 0.0,
                "porCategoria", List.of(),
                "masVendidos", List.of()));
        return "admin/reportes";
    }
}
