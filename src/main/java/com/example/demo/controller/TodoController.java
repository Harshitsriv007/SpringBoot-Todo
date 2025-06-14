package com.example.demo.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TODO;
import com.example.demo.services.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    // todo create
    @PostMapping
    public TODO create(@RequestBody TODO todo) {
        return todoService.create(todo);
    }

    @GetMapping
    public List<TODO> list() {
        return todoService.list();
    }

    // Update a todo
    @PatchMapping // selective item
    public List<TODO> update(@RequestBody TODO todo) {
        return todoService.updateTodo(todo);
    }

    // Remove a todo
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) { // Changed @PathParam to @PathVariable
        todoService.removeTodo(id);
        return "removed todo :" + id;
    }
}
