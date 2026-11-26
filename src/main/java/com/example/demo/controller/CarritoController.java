package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.model.Venta;
import com.example.demo.service.CarritoService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.example.demo.service.VentaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
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
    private final UsuarioService usuarioService;

    public CarritoController(CarritoService carritoService,
                             ProductoService productoService,
                             VentaService ventaService,
                             UsuarioService usuarioService) {
        this.carritoService = carritoService;
        this.productoService = productoService;
        this.ventaService = ventaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("carrito")
    public String carrito(Model model) {
        model.addAttribute("items", carritoService.listar());
        model.addAttribute("total", carritoService.total());
        model.addAttribute("recomendados", productoService.listarProductos());
        return "tienda/carrito";
    }

    @PostMapping("carrito/agregar")
    public String agregar(@RequestParam Long id, HttpServletRequest request) {
        carritoService.agregarCarrito(id);
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
    public String checkout(Authentication auth, Model model) {
        if (carritoService.listar().isEmpty()) {
            return "redirect:/carrito";
        }
        double subtotal = carritoService.total();
        double envio = carritoService.calcularEnvio(subtotal);
        double totalPagar = subtotal + envio;

        model.addAttribute("usuario", usuarioService.buscarPorEmail(auth.getName()));
        model.addAttribute("items", carritoService.listar());
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("envio", envio);
        model.addAttribute("totalPagar", totalPagar);

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
                            Authentication auth) {
        if (carritoService.listar().isEmpty()) {
            return "redirect:/carrito";
        }
        Usuario usuario = usuarioService.buscarPorEmail(auth.getName());
        double envio = carritoService.calcularEnvio(carritoService.total());
        Venta venta = ventaService.crearVenta(usuario, carritoService.listar(), nombre, direccion, envio);
        carritoService.vaciar();
        return "redirect:/pago-resultado?id=" + venta.getId();
    }
}