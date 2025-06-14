package com.example.demo.controller;

import com.example.demo.model.TODO;
import com.example.demo.services.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void testCreateTodo() throws Exception {
        TODO todo = new TODO(1, "Learn Spring Boot");

        when(todoService.create(Mockito.any(TODO.class))).thenReturn(todo);

        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"content\":\"Learn Spring Boot\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("Learn Spring Boot"));
    }

    @Test
    void testListTodos() throws Exception {
        TODO todo1 = new TODO(1, "Learn Spring Boot");
        TODO todo2 = new TODO(2, "Learn Testing");

        when(todoService.list()).thenReturn(Arrays.asList(todo1, todo2));

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].content").value("Learn Spring Boot"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].content").value("Learn Testing"));
    }

    @Test
    void testUpdateTodo() throws Exception {
        TODO updatedTodo = new TODO(1, "Master Spring Boot");

        when(todoService.updateTodo(Mockito.any(TODO.class))).thenReturn(Collections.singletonList(updatedTodo));

        mockMvc.perform(patch("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"content\":\"Master Spring Boot\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].content").value("Master Spring Boot"));
    }

    @Test
    void testRemoveTodo() throws Exception {
        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("removed todo :1")); // Update to match the actual response
    }
}
