package com.example.marcosproyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO Avance 2: /admin/dashboard, /admin/compras, /admin/reportes.
@Controller
@RequestMapping("/admin")

public class AdminDashboardController {
    @GetMapping("")
    public String admin(Model model) {
        return "admin/dashboard";
    }
}
