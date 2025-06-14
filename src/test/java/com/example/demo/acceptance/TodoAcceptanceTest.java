package com.example.demo.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    private void createTodo(int id, String content) throws Exception {
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"id\": %d, \"content\": \"%s\" }", id, content)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.content").value(content));
    }

    private void updateTodo(int id, String content) throws Exception {
        mockMvc.perform(patch("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"id\": %d, \"content\": \"%s\" }", id, content)))
                .andExpect(status().isOk());
    }

    private void deleteTodo(int id) throws Exception {
        mockMvc.perform(delete("/todos/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("removed todo :" + id));
    }

    @Test
    void testCreateAndListTodo() throws Exception {
        createTodo(1, "Sample TODO");

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].content").value("Sample TODO"));
    }

    @Test
    void testUpdateTodo() throws Exception {
        createTodo(1, "Sample TODO");

        updateTodo(1, "Updated TODO");

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Updated TODO"));
    }

    @Test
    void testDeleteTodo() throws Exception {
        createTodo(1, "Sample TODO");

        deleteTodo(1);

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
