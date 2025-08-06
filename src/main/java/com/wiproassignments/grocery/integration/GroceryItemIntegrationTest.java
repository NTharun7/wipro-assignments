package com.wiproassignments.grocery.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiproassignments.grocery.model.GroceryItem;
import com.wiproassignments.grocery.repository.GroceryItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroceryItemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GroceryItemRepository repository;

    @Test
    public void testCreateAndGetItem() throws Exception {
        GroceryItem item = new GroceryItem(null, "Banana", 10, 15.0);

        String json = new ObjectMapper().writeValueAsString(item);

        // Create
        mockMvc.perform(post("/api/grocery")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Banana"));

        // Get All
        mockMvc.perform(get("/api/grocery"))
                .andExpect(status().isOk());
    }
}
