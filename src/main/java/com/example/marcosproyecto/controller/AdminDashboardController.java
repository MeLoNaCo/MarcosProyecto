package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.model.Usuario;
import com.example.marcosproyecto.model.Venta;
import com.example.marcosproyecto.service.UsuarioService;
import com.example.marcosproyecto.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Resumen con datos reales del dummy (ventas por fecha, usuarios activos).
@Controller
public class AdminDashboardController {

    private final VentaService ventaService;
    private final UsuarioService usuarioService;

    public AdminDashboardController(VentaService ventaService, UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping({"/admin", "/admin/dashboard"})
    public String dashboard(Model model) {
        LocalDate hoy = LocalDate.now();
        int comprasHoy = 0;
        int comprasSemana = 0;
        double ingresosMes = 0;
        for (Venta v : ventaService.listarTodas()) {
            LocalDate f = v.getFecha().toLocalDate();
            if (f.equals(hoy)) {
                comprasHoy = comprasHoy + 1;
            }
            if (!f.isBefore(hoy.minusDays(7))) {
                comprasSemana = comprasSemana + 1;
            }
            if (f.getMonth() == hoy.getMonth() && f.getYear() == hoy.getYear()) {
                ingresosMes = ingresosMes + v.getTotal();
            }
        }
        int clientesNuevos = 0;
        for (Usuario u : usuarioService.listar()) {
            if (u.isActivo()) {
                clientesNuevos = clientesNuevos + 1;
            }
        }
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("comprasHoy", comprasHoy);
        resumen.put("comprasSemana", comprasSemana);
        resumen.put("ingresosMes", ingresosMes);
        resumen.put("clientesNuevos", clientesNuevos);
        model.addAttribute("resumen", resumen);
        return "admin/dashboard";
    }
}
