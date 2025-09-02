package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.*;
import com.capstoneproject.onlineshopping.repository.CartItemRepository;
import com.capstoneproject.onlineshopping.repository.OrderItemRepository;
import com.capstoneproject.onlineshopping.repository.OrderRepository;
import com.capstoneproject.onlineshopping.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        CartItemRepository cartItemRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    // ✅ Customer places order from cart (with address)
    public Order placeOrder(Long userId, String address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty!");
        }

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);   // ✅ store shipping address

        double total = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());

            total += orderItem.getPrice();
            orderItems.add(orderItem);
        }

        order.setTotalAmount(total);
        order.setItems(orderItems);

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Clear cart after placing order
        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    // ✅ Customer views their orders
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // ✅ Admin updates status
    public Order updateOrderStatus(Long orderId, Order.Status status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }

    // ✅ Admin views all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
