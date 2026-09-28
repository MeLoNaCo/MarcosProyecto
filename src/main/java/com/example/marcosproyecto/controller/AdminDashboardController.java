package com.example.marcosproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

// TODO Avance 2: métricas reales cuando tengas el módulo de reportes.
@Controller
public class AdminDashboardController {


    @GetMapping({"/dashboard"})
    public String dashboard(Model model) {
        return "admin/dashboard";
    }
}
