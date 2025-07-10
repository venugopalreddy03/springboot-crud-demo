package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        product1 = new Product(1L, "Laptop", "High performance laptop", 999.99, 10);
        product2 = new Product(2L, "Phone", "Smart phone", 699.99, 15);
    }

    @Test
    public void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById_Success() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Product foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals(product1.getName(), foundProduct.getName());
    }

    @Test
    public void testGetProductById_NotFound() {
        when(productRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getProductById(100L);
        });
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(product1)).thenReturn(product1);

        Product savedProduct = productService.createProduct(product1);

        assertNotNull(savedProduct);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void testUpdateProduct() {
        Product updatedDetails = new Product(null, "Updated Laptop", "New model", 1099.99, 5);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.save(product1)).thenReturn(product1);

        Product updatedProduct = productService.updateProduct(1L, updatedDetails);

        assertEquals(updatedDetails.getName(), updatedProduct.getName());
        assertEquals(updatedDetails.getDescription(), updatedProduct.getDescription());
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void testDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        doNothing().when(productRepository).delete(product1);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product1);
    }
}