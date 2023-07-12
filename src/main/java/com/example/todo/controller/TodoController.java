package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.todo.service.*;
import com.example.todo.model.*;


@RestController
public class TodoController{

    @Autowired
    private TodoH2Service service;

    @GetMapping("/todos")
    public ArrayList<Todo> getAllTodos(){
        return service.getAllTodos();
    }
    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable ("id") int id){
        return service.getTodoById(id);
    }
    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo){
        return service.addTodo(todo);
    }
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable ("id") int id,@RequestBody Todo todo){
        return service.updateTodo(id,todo);
    }

     @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable ("id") int id){
         service.deleteTodo(id);
    }
}
