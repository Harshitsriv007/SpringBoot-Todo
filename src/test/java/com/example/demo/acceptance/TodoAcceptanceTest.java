package com.example.demo.acceptance;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TodoAcceptanceTest extends MainAcceptanceTest {

    @Test
    void testCreateAndListTodo() throws Exception {
        // Use shared create method
        createTodo(1, "Sample TODO");

        // List TODOs
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].content").value("Sample TODO"));
    }

    @Test
    void testUpdateTodo() throws Exception {
        // Use shared create method
        createTodo(1, "Sample TODO");

        // Use shared update method
        updateTodo(1, "Updated TODO");

        // Verify the update
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Updated TODO"));
    }

    @Test
    void testDeleteTodo() throws Exception {
        // Use shared create method
        createTodo(1, "Sample TODO");

        // Use shared delete method
        deleteTodo(1);

        // Verify deletion
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
