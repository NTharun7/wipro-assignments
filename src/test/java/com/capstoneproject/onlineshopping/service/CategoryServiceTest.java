package com.capstoneproject.onlineshopping.service;

import com.capstoneproject.onlineshopping.entity.Category;
import com.capstoneproject.onlineshopping.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCategories() {
        Category c1 = new Category();
        c1.setId(1L);
        c1.setName("Electronics");

        Category c2 = new Category();
        c2.setId(2L);
        c2.setName("Clothing");

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Category> categories = categoryService.getAllCategories();

        assertEquals(2, categories.size());
        assertEquals("Electronics", categories.get(0).getName());
    }

    @Test
    void testGetCategoryById_Found() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Books");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> found = categoryService.getCategoryById(1L);

        assertTrue(found.isPresent());
        assertEquals("Books", found.get().getName());
    }

    @Test
    void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Category> found = categoryService.getCategoryById(1L);

        assertFalse(found.isPresent());
    }

    @Test
    void testSaveCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Furniture");

        when(categoryRepository.save(category)).thenReturn(category);

        Category saved = categoryService.saveCategory(category);

        assertNotNull(saved);
        assertEquals("Furniture", saved.getName());
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;

        doNothing().when(categoryRepository).deleteById(categoryId);

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
