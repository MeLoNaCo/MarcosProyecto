package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {

    @GetMapping({"/catalogo"})
    public String catalogo(Model model){
        return "tienda/catalogo";
    }
}
