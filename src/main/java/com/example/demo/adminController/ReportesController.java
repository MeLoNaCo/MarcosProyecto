package com.example.demo.adminController;

import com.example.demo.service.ReporteService;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportesController {

    private final ReporteService reporteService;
    private final UsuarioService usuarioService;

    public ReportesController(ReporteService reporteService, UsuarioService usuarioService) {
        this.reporteService = reporteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/admin/reportes")
    public String reportes(Model model) {
        ReporteService.Reporte r = reporteService.generarReporte();

        model.addAttribute("totalVentasG", r.totalMes());
        model.addAttribute("totalOrdenesG", r.ordenesMes());
        model.addAttribute("ticketPromedioG", r.ticketMes());
        model.addAttribute("totalClientes", usuarioService.contarUsuarios());

        model.addAttribute("meses", r.meses());
        model.addAttribute("ventas", r.ventas());
        model.addAttribute("pedidos", r.pedidos());
        model.addAttribute("clientes", r.compradores());
        model.addAttribute("productos", r.productos());
        model.addAttribute("ticket", r.ticket());

        model.addAttribute("categorias", reporteService.ventasPorCategoria());
        model.addAttribute("topProductos", reporteService.productosMasVendidos(5));
        return "admin/reportes";
    }
}