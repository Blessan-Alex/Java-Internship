package com.example;

import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Task testTask;

    @BeforeEach
    void setUp() {
        testTask = new Task("Test Task", "Test Description");
        testTask.setId(1L);
    }

    // Test GET all tasks - Success
    @Test
    void testGetAllTasks_Success() throws Exception {
        when(taskRepository.findAll()).thenReturn(Arrays.asList(testTask));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test Task"))
                .andExpect(jsonPath("$[0].description").value("Test Description"));

        verify(taskRepository, times(1)).findAll();
    }

    // Test GET all tasks - Empty list
    @Test
    void testGetAllTasks_EmptyList() throws Exception {
        when(taskRepository.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

        verify(taskRepository, times(1)).findAll();
    }

    // Test GET task by ID - Success
    @Test
    void testGetTaskById_Success() throws Exception {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"));

        verify(taskRepository, times(1)).findById(1L);
    }

    // Test GET task by ID - Not found
    @Test
    void testGetTaskById_NotFound() throws Exception {
        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/tasks/999"))
                .andExpect(status().isNotFound());

        verify(taskRepository, times(1)).findById(999L);
    }

    // Test POST create task - Success
    @Test
    void testCreateTask_Success() throws Exception {
        Task newTask = new Task("New Task", "New Description");
        Task savedTask = new Task("New Task", "New Description");
        savedTask.setId(2L);

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.description").value("New Description"));

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    // Test POST create task - Invalid data
    @Test
    void testCreateTask_InvalidData() throws Exception {
        Task invalidTask = new Task("", ""); // Empty title and description

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidTask)))
                .andExpect(status().isBadRequest());

        verify(taskRepository, never()).save(any(Task.class));
    }

    // Test PUT update task - Success
    @Test
    void testUpdateTask_Success() throws Exception {
        Task updateData = new Task("Updated Task", "Updated Description");
        Task existingTask = new Task("Original Task", "Original Description");
        existingTask.setId(1L);
        Task updatedTask = new Task("Updated Task", "Updated Description");
        updatedTask.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        mockMvc.perform(put("/api/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated Description"));

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    // Test PUT update task - Not found
    @Test
    void testUpdateTask_NotFound() throws Exception {
        Task updateData = new Task("Updated Task", "Updated Description");

        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/tasks/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isNotFound());

        verify(taskRepository, times(1)).findById(999L);
        verify(taskRepository, never()).save(any(Task.class));
    }

    // Test DELETE task - Success
    @Test
    void testDeleteTask_Success() throws Exception {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        doNothing().when(taskRepository).deleteById(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    // Test DELETE task - Not found
    @Test
    void testDeleteTask_NotFound() throws Exception {
        when(taskRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/tasks/999"))
                .andExpect(status().isNotFound());

        verify(taskRepository, times(1)).findById(999L);
        verify(taskRepository, never()).deleteById(any(Long.class));
    }

    // Test exception handling
    @Test
    void testGetAllTasks_Exception() throws Exception {
        when(taskRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isInternalServerError());

        verify(taskRepository, times(1)).findAll();
    }
}
