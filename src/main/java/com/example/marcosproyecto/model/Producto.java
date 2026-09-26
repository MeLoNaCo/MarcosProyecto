package com.example.marcosproyecto.model;

// Avance 2: POJO dummy (sin @Entity). En Avance 3 agregar @Entity + JPA.
public class Producto {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagenUrl;
    private int oferta; // 0-90, default 0. Es el atributo oferta.
    private boolean activo = true;
    private Categoria categoria;

    public Producto() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = Math.max(0, precio); }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = Math.max(0, stock); }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public int getOferta() { return oferta; }

    public void setOferta(int oferta) {
        if (oferta < 0) oferta = 0;
        if (oferta > 90) oferta = 90;
        this.oferta = oferta;
    }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    // Regla: se aplica el mayor entre oferta del producto y descuento de categoría, nunca se suman.
    public double getPrecioFinal() {
        int pctCat = (categoria != null) ? categoria.getDescuentoPct() : 0;
        int pct = Math.max(oferta, pctCat);
        return precio * (1 - pct / 100.0);
    }
}
