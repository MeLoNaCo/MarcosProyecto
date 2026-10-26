package com.example.demo.service;

import com.example.demo.adapter.VentaAdapter;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.entity.VentaEntity;
import com.example.demo.model.DetalleVenta;
import com.example.demo.model.Usuario;
import com.example.demo.model.Venta;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.VentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {


    private final VentaRepository ventaRepository;
    private final VentaAdapter ventaAdapter;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, VentaAdapter ventaAdapter, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.ventaAdapter = ventaAdapter;
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public long contarVentas() {
        return ventaRepository.count();
    }

    @Transactional(readOnly = true)
    public List<Venta> listarVentas() {
        return ventaRepository.findAll().stream()
                .map(ventaAdapter::toModel)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Venta> listarPorUsuario(Long idUsuario) {
        return ventaRepository.findByUsuarioIdOrderByFechaDesc(idUsuario).stream()
                .map(ventaAdapter::toModel)
                .collect(Collectors.toList());
    }

    @Transactional
    public Venta crearVenta(Usuario usuario, List<DetalleVenta> items, String nombre, String direccion) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("La venta requiere un usuario");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        // 1. Validar y descontar stock (esto es lo nuevo)
        for (DetalleVenta d : items) {
            ProductoEntity p = productoRepository.findById(d.getProducto().getId())
                    .orElseThrow(() -> new IllegalStateException("Producto no encontrado"));
            if (!p.isActivo() || p.getStock() < d.getCantidad()) {
                throw new IllegalStateException(
                        "Stock insuficiente para " + p.getNombre() + " (quedan " + p.getStock() + ")");
            }
            p.setStock(p.getStock() - d.getCantidad());
        }

        // 2. Armar y guardar la venta (lo que ya tenías)
        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setIdUsuario(usuario.getId());
        venta.setNombreComprador(nombre != null && !nombre.isBlank() ? nombre : usuario.getNombre());
        venta.setDireccion(direccion);
        venta.setDetalles(items);

        VentaEntity saved = ventaRepository.save(ventaAdapter.toEntity(venta));
        venta.setId(saved.getId());
        return venta;
    }

    @Transactional(readOnly = true)
    public Venta buscarPorId(Long id) {
        VentaEntity entity = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
        return ventaAdapter.toModel(entity);
    }

    @Transactional(readOnly = true)
    public double contarIngresosTotales() {
        return ventaRepository.sumarTotalVentas();
    }
}
