package com.example.demo.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping({"/admin","/admin/dashboard"})
    public String dashboard(Model model){
        return "admin/dashboard";
    }
}
