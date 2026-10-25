package com.example.demo.model;

public class Producto {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagenUrl;
    private int oferta;
    private boolean activo = true;
    private Long id_categoria;
    private String nombreCategoria;

    public Producto() {
    }

    public Producto(Long id, String nombre, String descripcion, double precio,
        int stock, String imagenUrl, int oferta, long id_categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        setPrecio(precio);
        setStock(stock);
        this.imagenUrl = imagenUrl;
        setOferta(oferta);
        this.id_categoria = id_categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = Math.max(0, precio);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = Math.max(0, stock);
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        if (oferta < 0) oferta = 0;
        if (oferta > 90) oferta = 90;
        this.oferta = oferta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Long getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public double getPrecioFinal() {
        return precio * (1.0 - (double) oferta / 100.0);
    }
}