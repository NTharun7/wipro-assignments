package com.capstoneproject.onlineshopping.controller;

import com.capstoneproject.onlineshopping.dto.CartRequest;
import com.capstoneproject.onlineshopping.entity.CartItem;
import com.capstoneproject.onlineshopping.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Swagger Annotations
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/cart")  
@Tag(name = "Cart", description = "Shopping cart management APIs")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // ✅ POST /api/cart → Add item to cart
    @PostMapping
    @Operation(
        summary = "Add item to cart",
        description = "Adds a product to a user's cart with specified quantity",
        responses = {
            @ApiResponse(responseCode = "200", description = "Item added to cart successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
        }
    )
    public ResponseEntity<CartItem> addToCart(@RequestBody CartRequest request) {
        return ResponseEntity.ok(
            cartService.addToCart(request.getUserId(), request.getProductId(), request.getQuantity())
        );
    }

    // ✅ GET /api/cart/{userId} → View user’s cart
    @GetMapping("/{userId}")
    @Operation(
        summary = "Get user's cart",
        description = "Fetch all items in a specific user's shopping cart",
        responses = {
            @ApiResponse(responseCode = "200", description = "Cart fetched successfully"),
            @ApiResponse(responseCode = "404", description = "User or cart not found")
        }
    )
    public ResponseEntity<List<CartItem>> getCartByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }

    // ✅ DELETE /api/cart/{id} → Remove product from cart
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remove item from cart",
        description = "Removes a specific item from the cart by its cartItem ID",
        responses = {
            @ApiResponse(responseCode = "204", description = "Item removed successfully"),
            @ApiResponse(responseCode = "404", description = "Cart item not found")
        }
    )
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }
}
