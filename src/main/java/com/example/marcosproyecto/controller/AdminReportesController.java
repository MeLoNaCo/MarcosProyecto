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
        return "admin/reportes";
    }
}
