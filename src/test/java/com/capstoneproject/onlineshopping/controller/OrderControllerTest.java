package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.entity.Order;
import com.capstoneproject.onlineshopping.service.OrderService;
import com.capstoneproject.onlineshopping.security.JwtUtils;
import com.capstoneproject.onlineshopping.security.AuthTokenFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OrderController.class,
        excludeAutoConfiguration = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
        })
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    // ✅ Mock security beans so Spring doesn’t try to load full context
    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private AuthTokenFilter authTokenFilter;

    @Test
    void testPlaceOrder() throws Exception {
        Order order = new Order();
        order.setId(1L);

        Mockito.when(orderService.placeOrder(eq(1L), eq("Hyderabad")))
                .thenReturn(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":1,\"address\":\"Hyderabad\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testGetUserOrders() throws Exception {
        Order order1 = new Order();
        order1.setId(1L);
        Order order2 = new Order();
        order2.setId(2L);

        List<Order> orders = Arrays.asList(order1, order2);
        Mockito.when(orderService.getUserOrders(1L)).thenReturn(orders);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testUpdateOrderStatus() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setStatus(Order.Status.CONFIRMED);

        Mockito.when(orderService.updateOrderStatus(eq(1L), eq(Order.Status.CONFIRMED)))
                .thenReturn(order);

        mockMvc.perform(put("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"CONFIRMED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONFIRMED"));
    }
}
