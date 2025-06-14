package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.TODO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TodoServiceTest {

    private TodoService todoService;

    @BeforeEach
    void setUp() {
        todoService = new TodoService();
    }

    @Test
    void testCreateTodo() {
        TODO todo = new TODO(1, "Learn Spring Boot");
        TODO createdTodo = todoService.create(todo);

        assertEquals(todo.getId(), createdTodo.getId());
        assertEquals(todo.getContent(), createdTodo.getContent());
    }

    @Test
    void testListTodos() {
        TODO todo1 = new TODO(1, "Learn Spring Boot");
        TODO todo2 = new TODO(2, "Learn Testing");
        todoService.create(todo1);
        todoService.create(todo2);

        List<TODO> todos = todoService.list();

        assertEquals(2, todos.size());
        assertTrue(todos.contains(todo1));
        assertTrue(todos.contains(todo2));
    }

    @Test
    void testUpdateTodo() {
        TODO todo = new TODO(1, "Learn Spring Boot");
        todoService.create(todo);

        TODO updatedTodo = new TODO(1, "Master Spring Boot");
        List<TODO> updatedList = todoService.updateTodo(updatedTodo);

        assertEquals(1, updatedList.size());
        assertEquals("Master Spring Boot", updatedList.get(0).getContent());
    }

    @Test
    void testRemoveTodo() {
        TODO todo = new TODO(1, "Learn Spring Boot");
        todoService.create(todo);

        todoService.removeTodo(1);

        List<TODO> todos = todoService.list();
        assertTrue(todos.isEmpty());
    }

    @Test
    void testRemoveTodoNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            todoService.removeTodo(1);
        });

        assertEquals("TODO with ID 1 not found.", exception.getMessage());
    }
}
