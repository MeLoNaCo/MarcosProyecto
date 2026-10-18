package com.example.demo.model;

import java.time.LocalDate;

public class Task {

    private String titulo;
    private LocalDate fechaCreacion;
    private Long id_categoria;

    public Task(String titulo, LocalDate fechaCreacion) {
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
    }

    public Task(String titulo, LocalDate fechaCreacion, Long id_categoria) {
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.id_categoria = id_categoria;
    }

    public Task() {
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
