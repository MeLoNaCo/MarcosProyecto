package com.example.demo.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportesController {

    @GetMapping({"/reportes","/admin/reportes"})
    public String reportes(Model model){

        return "admin/reportes";
    }
}