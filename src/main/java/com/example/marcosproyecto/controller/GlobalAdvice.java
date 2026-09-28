package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.service.CarritoService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// Pone el contador del carrito en el Model de TODAS las páginas,
// para que el navbar lo muestre sin tocar cada controller.
@ControllerAdvice
public class GlobalAdvice {

    private final CarritoService carritoService;

    public GlobalAdvice(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @ModelAttribute("carritoCount")
    public int carritoCount() {
        return carritoService.totalCantidad();
    }
}
