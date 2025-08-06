package com.wiproassignments.grocery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiproassignments.grocery.model.GroceryItem;
import com.wiproassignments.grocery.service.GroceryItemService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.*;

@WebMvcTest(GroceryItemController.class)
public class GroceryItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroceryItemService service;

    private GroceryItem item;

    @Before
    public void setUp() {
        item = new GroceryItem(1L, "Rice", 5, 45.5);
    }

    @Test
    public void testGetAll() throws Exception {
        when(service.findAll()).thenReturn(Arrays.asList(item));

        mockMvc.perform(get("/api/grocery"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rice"));
    }

    @Test
    public void testGetById() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(item));

        mockMvc.perform(get("/api/grocery/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rice"));
    }

    @Test
    public void testCreate() throws Exception {
        when(service.save(any(GroceryItem.class))).thenReturn(item);

        mockMvc.perform(post("/api/grocery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rice"));
    }

    @Test
    public void testUpdate() throws Exception {
        when(service.update(eq(1L), any(GroceryItem.class))).thenReturn(item);

        mockMvc.perform(put("/api/grocery/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rice"));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/api/grocery/1"))
                .andExpect(status().isOk());
    }
}
