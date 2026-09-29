package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.model.Venta;
import com.example.marcosproyecto.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminReportesController {

    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping("/admin/reportes")
    public String verReportes(Model model) {
        List<Venta> listaVentas = ventaRepository.findAll();

        double sumaVentas = 0;
        int sumaProductos = 0;

        for (Venta v : listaVentas) {
            sumaVentas = sumaVentas + v.getTotal();
            sumaProductos = sumaProductos + v.getDetalles().size();
        }

        int totalPedidos = listaVentas.size();
        double ticketPromedio = 0;
        if (totalPedidos > 0) {
            ticketPromedio = sumaVentas / totalPedidos;
        }

        model.addAttribute("meses", Arrays.asList("Mayo", "Junio", "Julio", "Agosto", "Septiembre"));
        model.addAttribute("ventas", Arrays.asList(3500.0, 4200.0, 5100.0, 6800.0, sumaVentas));
        model.addAttribute("pedidos", Arrays.asList(20, 28, 35, 42, totalPedidos));
        model.addAttribute("clientes", Arrays.asList(8, 12, 15, 22, 25));
        model.addAttribute("productos", Arrays.asList(45, 60, 78, 95, sumaProductos));
        model.addAttribute("ticket", Arrays.asList(175.0, 150.0, 145.0, 161.0, ticketPromedio));

        model.addAttribute("totalVentasG", sumaVentas);
        model.addAttribute("totalOrdenesG", totalPedidos);
        model.addAttribute("ticketPromedioG", ticketPromedio);

        return "admin/reportes";
    }
}