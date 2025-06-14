package com.example.demo.acceptance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest // Starts the entire Spring context for testing
@AutoConfigureMockMvc // Injects MockMvc for simulating HTTP requests
class TodoAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateAndListTodo() throws Exception {
        // Create a TODO
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"content\": \"Sample TODO\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("Sample TODO"));

        // List TODOs
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].content").value("Sample TODO"));
    }

    @Test
    void testUpdateTodo() throws Exception {
        // Create a TODO
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"content\": \"Sample TODO\" }"))
                .andExpect(status().isOk());

        // Update the TODO
        mockMvc.perform(patch("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"content\": \"Updated TODO\" }"))
                .andExpect(status().isOk());

        // Verify the update
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Updated TODO"));
    }

    @Test
    void testDeleteTodo() throws Exception {
        // Create a TODO
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"content\": \"Sample TODO\" }"))
                .andExpect(status().isOk());

        // Delete the TODO
        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("removed todo :1"));

        // Verify deletion
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
