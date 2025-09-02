package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.CartItem;
import com.capstoneproject.onlineshopping.entity.Product;
import com.capstoneproject.onlineshopping.entity.User;
import com.capstoneproject.onlineshopping.repository.CartItemRepository;
import com.capstoneproject.onlineshopping.repository.ProductRepository;
import com.capstoneproject.onlineshopping.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository,
                       UserRepository userRepository,
                       ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // ✅ Add or update cart item
    public CartItem addToCart(Long userId, Long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // check if product already exists in this user's cart
        Optional<CartItem> existingItem = cartItemRepository.findByUserAndProductId(user, product.getId());

        if (existingItem.isPresent()) {
            // update quantity
            CartItem existingCartItem = existingItem.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            return cartItemRepository.save(existingCartItem);
        } else {
            // new product in cart
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            return cartItemRepository.save(newCartItem);
        }
    }

    // ✅ View cart for a user
    public List<CartItem> getUserCart(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    // ✅ Remove from cart
    public void removeFromCart(Long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new RuntimeException("Cart item not found");
        }
        cartItemRepository.deleteById(cartItemId);
    }
}
