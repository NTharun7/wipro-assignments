package com.wiproassignments.grocery.service;

import com.wiproassignments.grocery.model.GroceryItem;
import com.wiproassignments.grocery.repository.GroceryItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryItemService {

    private final GroceryItemRepository repository;

    public GroceryItemService(GroceryItemRepository repository) {
        this.repository = repository;
    }

    public List<GroceryItem> findAll() {
        return repository.findAll();
    }

    public Optional<GroceryItem> findById(Long id) {
        return repository.findById(id);
    }

    public GroceryItem save(GroceryItem item) {
        return repository.save(item);
    }

    public GroceryItem update(Long id, GroceryItem newItem) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(newItem.getName());
                    existing.setQuantity(newItem.getQuantity());
                    existing.setPrice(newItem.getPrice());
                    return repository.save(existing);
                }).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
