package com.example.demo.entity;

import jakarta.persistence.*;

import java.beans.ConstructorProperties;
import java.time.LocalDate;


@Entity (name="task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @Column(name="fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    private CategoriaEntity categoria;

    public TaskEntity(Long id, String titulo, LocalDate fechaCreacion) {
        this.id = id;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
    }

    public TaskEntity() {
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
