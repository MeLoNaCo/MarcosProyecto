package com.example.demo.entity;

import com.example.demo.model.DetalleVenta;
import com.example.demo.model.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "venta")
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha")
    private LocalDateTime fecha;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario", nullable = false)
    private UsuarioEntity usuario;

    private String nombreComprador;
    private String direccion;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntity> detalles = new ArrayList<>();

    private double total;
    private String estado = "PAGADO";

    public VentaEntity() {
    }

    public VentaEntity(Long id, LocalDateTime fecha, UsuarioEntity usuario, String nombreComprador, String direccion, List<DetalleVentaEntity> detalles, double total, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.usuario = usuario;
        this.nombreComprador = nombreComprador;
        this.direccion = direccion;
        this.detalles = detalles;
        this.total = total;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<DetalleVentaEntity> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaEntity> detalles) {
        this.detalles = detalles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
