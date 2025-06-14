package com.example.demo.acceptance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class MainAcceptanceTest {

    @Autowired
    protected MockMvc mockMvc;

    protected void createTodo(int id, String content) throws Exception {
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"id\": %d, \"content\": \"%s\" }", id, content)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.content").value(content));
    }

    protected void updateTodo(int id, String content) throws Exception {
        mockMvc.perform(patch("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{ \"id\": %d, \"content\": \"%s\" }", id, content)))
                .andExpect(status().isOk());
    }

    protected void deleteTodo(int id) throws Exception {
        mockMvc.perform(delete("/todos/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("removed todo :" + id));
    }
}
