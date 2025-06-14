package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.model.TODO;

@Service
public class TodoService {

    private List<TODO> list = new ArrayList<>();

    public TODO create(TODO todo) {
        list.add(todo);
        return todo;
    }

    public List<TODO> list() {
        return list;
    }

    public List<TODO> updateTodo(TODO todo) {
        TODO existingTodo = list.stream()
                .filter(t -> t.getId() == todo.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("TODO with ID " + todo.getId() + " not found."));
        existingTodo.setContent(todo.getContent()); // Update the content
        return list; // Return the updated list
    }

    public void removeTodo(Integer id) {
        boolean removed = list.removeIf(todo -> todo.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("TODO with ID " + id + " not found.");
        }
    }

}
