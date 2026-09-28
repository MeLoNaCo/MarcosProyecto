package com.example.marcosproyecto.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Cabecera del pedido: una venta agrupa sus detalles + cliente + total.
public class Venta {

    private Long id;
    private LocalDateTime fecha;
    private Usuario usuario;
    private String nombreComprador;
    private String direccion;
    private List<DetalleVenta> detalles = new ArrayList<>();
    private double total;
    private String estado = "PAGADO";

    public Venta() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNombreComprador() { return nombreComprador; }
    public void setNombreComprador(String nombreComprador) { this.nombreComprador = nombreComprador; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles) { this.detalles = detalles; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
