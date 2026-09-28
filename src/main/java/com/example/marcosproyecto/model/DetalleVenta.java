package com.example.marcosproyecto.model;

// Una fila del carrito y del pedido: producto + cantidad.
public class DetalleVenta {

    private Producto producto;
    private int cantidad;

    public DetalleVenta() {
    }

    public DetalleVenta(Producto producto, int cantidad) {
        this.producto = producto;
        setCantidad(cantidad);
    }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = Math.max(1, cantidad);
    }

    public double getSubtotal() {
        if (producto == null) {
            return 0;
        }
        return producto.getPrecioFinal() * cantidad;
    }
}
