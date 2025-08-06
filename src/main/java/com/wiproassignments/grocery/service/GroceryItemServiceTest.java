package com.wiproassignments.grocery.service;

import com.wiproassignments.grocery.model.GroceryItem;
import com.wiproassignments.grocery.repository.GroceryItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GroceryItemServiceTest {

    @Mock
    private GroceryItemRepository repository;

    @InjectMocks
    private GroceryItemService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new GroceryItem(1L, "Apple", 2, 30.0)));
        assertEquals(1, service.findAll().size());
    }

    @Test
    public void testFindById() {
        GroceryItem item = new GroceryItem(1L, "Milk", 1, 20.0);
        when(repository.findById(1L)).thenReturn(Optional.of(item));
        Optional<GroceryItem> found = service.findById(1L);
        assertTrue(found.isPresent());
        assertEquals("Milk", found.get().getName());
    }

    @Test
    public void testSave() {
        GroceryItem item = new GroceryItem(null, "Bread", 2, 25.0);
        when(repository.save(item)).thenReturn(new GroceryItem(1L, "Bread", 2, 25.0));
        GroceryItem saved = service.save(item);
        assertNotNull(saved);
        assertEquals("Bread", saved.getName());
    }

    @Test
    public void testUpdate() {
        GroceryItem item = new GroceryItem(1L, "Eggs", 12, 60.0);
        when(repository.findById(1L)).thenReturn(Optional.of(item));
        when(repository.save(any())).thenReturn(item);
        GroceryItem updated = service.update(1L, item);
        assertEquals("Eggs", updated.getName());
    }

    @Test
    public void testDelete() {
        service.delete(1L);
        verify(repository, times(1)).deleteById(1L);
    }
}
