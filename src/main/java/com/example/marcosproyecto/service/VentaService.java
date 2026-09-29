package com.example.marcosproyecto.service;

import com.example.marcosproyecto.model.DetalleVenta;
import com.example.marcosproyecto.model.Usuario;
import com.example.marcosproyecto.model.Venta;
import com.example.marcosproyecto.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Reglas de negocio de venta: al vender descuenta stock y calcula el total.
@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    // Convierte el carrito en venta: foto de precios, descuenta stock, guarda y listo.
    public Venta crear(Usuario usuario, List<DetalleVenta> items, String nombre, String direccion) {
        List<DetalleVenta> copia = new ArrayList<>();
        double total = 0;
        for (DetalleVenta d : items) {
            copia.add(new DetalleVenta(d.getProducto(), d.getCantidad()));
            total = total + d.getSubtotal();
            d.getProducto().setStock(d.getProducto().getStock() - d.getCantidad());
        }
        Venta venta = new Venta();
        venta.setUsuario(usuario);
        venta.setNombreComprador(nombre);
        venta.setDireccion(direccion);
        venta.setFecha(LocalDateTime.now());
        venta.setDetalles(copia);
        venta.setTotal(total);
        venta.setEstado("PAGADO");
        return ventaRepository.save(venta);
    }

    public List<Venta> listarPorUsuario(Long usuarioId) {
        return ventaRepository.findByUsuarioId(usuarioId);
    }

    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }
}
