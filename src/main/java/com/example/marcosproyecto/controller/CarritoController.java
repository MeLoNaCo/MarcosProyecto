package com.example.marcosproyecto.controller;

import com.example.marcosproyecto.model.Usuario;
import com.example.marcosproyecto.model.Venta;
import com.example.marcosproyecto.service.CarritoService;
import com.example.marcosproyecto.service.ProductoService;
import com.example.marcosproyecto.service.VentaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarritoController {

    private final CarritoService carritoService;
    private final ProductoService productoService;
    private final VentaService ventaService;

    public CarritoController(CarritoService carritoService, ProductoService productoService,
                             VentaService ventaService) {
        this.carritoService = carritoService;
        this.productoService = productoService;
        this.ventaService = ventaService;
    }

    @GetMapping("/carrito")
    public String carrito(Model model) {
        model.addAttribute("items", carritoService.listar());
        model.addAttribute("total", carritoService.total());
        model.addAttribute("recomendados", productoService.recomendar(carritoService.listar()));
        return "tienda/carrito";
    }

    @PostMapping("/carrito/agregar")
    public String agregar(@RequestParam Long id, HttpServletRequest request) {
        carritoService.agregar(id);
        return "redirect:" + destino(request);
    }

    @PostMapping("/carrito/quitar")
    public String quitar(@RequestParam Long id, HttpServletRequest request) {
        carritoService.quitar(id);
        return "redirect:" + destino(request);
    }

    @PostMapping("/carrito/cantidad")
    public String cantidad(@RequestParam Long id, @RequestParam int cantidad, HttpServletRequest request) {
        carritoService.cambiarCantidad(id, cantidad);
        return "redirect:" + destino(request);
    }

    private String destino(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer == null || referer.isBlank()) {
            return "/catalogo";
        }
        return referer;
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        if (carritoService.listar().isEmpty()) {
            return "redirect:/carrito";
        }
        model.addAttribute("items", carritoService.listar());
        model.addAttribute("total", carritoService.total());
        return "tienda/checkout";
    }

    @GetMapping("/pago-resultado")
    public String pagoResultado(@RequestParam(required = false) Long id, Model model) {
        Venta venta = (id == null) ? null : ventaService.buscarPorId(id);
        if (venta == null) {
            return "redirect:/carrito";
        }
        model.addAttribute("venta", venta);
        return "tienda/pago-resultado";
    }

    @PostMapping("/checkout/confirmar")
    public String confirmar(@RequestParam String nombre,
                            @RequestParam String direccion,
                            HttpSession session) {
        Object obj = session.getAttribute("usuario");
        if (!(obj instanceof Usuario usuario)) {
            return "redirect:/login";
        }
        if (carritoService.listar().isEmpty()) {
            return "redirect:/carrito";
        }
        Venta venta = ventaService.crear(usuario, carritoService.listar(), nombre, direccion);
        carritoService.vaciar();
        return "redirect:/pago-resultado?id=" + venta.getId();
    }
}
