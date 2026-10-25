package com.example.demo.adapter;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.model.Task;
import com.example.demo.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskAdapter {

    // Convierte de Entity a Model
    public Task toModel(TaskEntity entity) {
        if (entity == null) {
            return null;
        }

        Task task = new Task();
        task.setTitulo(entity. getTitulo());
        task.setFechaCreacion(entity.getFechaCreacion());
        task.setId_categoria(entity.getCategoria().getId());
        return task;
    }

    // Convierte de Model a Entity
    public TaskEntity toEntity(Task model, CategoriaEntity categoria) {
        if (model == null) {
            return null;
        }
        TaskEntity entity = new TaskEntity();
        entity.setTitulo(model.getTitulo());
        entity.setFechaCreacion(model.getFechaCreacion());
        entity.setCategoria(categoria);  // Ahora recibe CategoriaEntity, no Long

        return entity;
    }
}
