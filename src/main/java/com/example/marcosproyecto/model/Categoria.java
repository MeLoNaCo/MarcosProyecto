package com.example.marcosproyecto.model;

// Avance 2: POJO dummy (sin @Entity). En Avance 3 agregar @Entity + JPA.
public class Categoria {

    private Long id;
    private String nombre;
    private String descripcion;
    private int descuentoPct; // 0-90, default 0
    private boolean activo = true;

    public Categoria() {
    }

    public Categoria(Long id, String nombre, int descuentoPct) {
        this.id = id;
        this.nombre = nombre;
        setDescuentoPct(descuentoPct);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getDescuentoPct() { return descuentoPct; }

    public void setDescuentoPct(int descuentoPct) {
        if (descuentoPct < 0) descuentoPct = 0;
        if (descuentoPct > 90) descuentoPct = 90;
        this.descuentoPct = descuentoPct;
    }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
