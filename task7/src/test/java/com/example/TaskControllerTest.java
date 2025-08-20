package com.example;

import com.example.model.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureWebMvc
public class TaskControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Test
    public void testCreateTask() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        Task task = new Task("Test Task", "Test Description");
        String taskJson = objectMapper.writeValueAsString(task);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    @Test
    public void testGetAllTasks() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetTaskById() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // First create a task
        Task task = new Task("Test Task", "Test Description");
        String taskJson = objectMapper.writeValueAsString(task);
        
        String response = mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andReturn().getResponse().getContentAsString();
        
        Task createdTask = objectMapper.readValue(response, Task.class);
        
        // Then get it by ID
        mockMvc.perform(get("/api/tasks/" + createdTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdTask.getId()))
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // First create a task
        Task task = new Task("Original Title", "Original Description");
        String taskJson = objectMapper.writeValueAsString(task);
        
        String response = mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andReturn().getResponse().getContentAsString();
        
        Task createdTask = objectMapper.readValue(response, Task.class);
        
        // Then update it
        Task updatedTask = new Task("Updated Title", "Updated Description");
        String updatedTaskJson = objectMapper.writeValueAsString(updatedTask);
        
        mockMvc.perform(put("/api/tasks/" + createdTask.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedTaskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.description").value("Updated Description"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // First create a task
        Task task = new Task("Task to Delete", "Will be deleted");
        String taskJson = objectMapper.writeValueAsString(task);
        
        String response = mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andReturn().getResponse().getContentAsString();
        
        Task createdTask = objectMapper.readValue(response, Task.class);
        
        // Then delete it
        mockMvc.perform(delete("/api/tasks/" + createdTask.getId()))
                .andExpect(status().isNoContent());
        
        // Verify it's deleted
        mockMvc.perform(get("/api/tasks/" + createdTask.getId()))
                .andExpect(status().isNotFound());
    }
}
