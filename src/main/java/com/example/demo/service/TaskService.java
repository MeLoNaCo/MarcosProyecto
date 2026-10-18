package com.example.demo.service;

import com.example.demo.adapter.TaskAdapter;
import com.example.demo.entity.TaskEntity;
import com.example.demo.model.Task;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private CategoriaRepository categoriaRepository;
    private TaskRepository taskRepository;
    private TaskAdapter taskAdapter;

    public TaskService(TaskRepository taskRepository, TaskAdapter taskAdapter,CategoriaRepository categoriaRepository) {
        this.taskRepository = taskRepository;
        this.taskAdapter = taskAdapter;
        this.categoriaRepository = categoriaRepository;
    }

    public Task crearTarea(Task task) {
        TaskEntity entity = taskAdapter.toEntity(task, categoriaRepository.findById(task.getId_categoria())
                .orElseThrow(() ->  new RuntimeException("Categoría no encontrada con ID: " + task.getId_categoria()) ));
        TaskEntity savedEntity = taskRepository.save(entity);
        return taskAdapter.toModel(savedEntity);
    }

    public List<Task> listarTareas() {
        List<TaskEntity> entities = taskRepository.findAll();

        // Convierte cada Entity a Model usando el adapter
        return entities.stream()
                .map( e -> taskAdapter.toModel(e) )
                .collect(Collectors.toList());
    }
}
