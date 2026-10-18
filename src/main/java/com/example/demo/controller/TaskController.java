package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.Task;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping("/guardar")
    public Task crear(@RequestBody Task task) {
        return service.crearTarea(task);
    }

    @GetMapping("/listar")
    public List<Task> listar() {
        return service.listarTareas();
    }
}