package com.example.demo.entity;

import com.example.demo.model.Producto;
import com.example.demo.model.Venta;
import jakarta.persistence.*;

@Entity(name = "DetalleVenta")
public class DetalleVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    private int cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    private VentaEntity venta;

    public DetalleVentaEntity() {
    }

    public DetalleVentaEntity(Long id, ProductoEntity producto, int cantidad,
                              double precioUnitario, VentaEntity venta) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.venta = venta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public VentaEntity getVenta() {
        return venta;
    }

    public void setVenta(VentaEntity venta) {
        this.venta = venta;
    }

    public double getSubTotal() {
        return precioUnitario * cantidad;
    }
}