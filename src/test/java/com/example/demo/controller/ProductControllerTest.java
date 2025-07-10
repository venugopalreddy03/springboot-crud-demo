package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp() {
        product1 = new Product(1L, "Laptop", "High performance laptop", 999.99, 10);
        product2 = new Product(2L, "Phone", "Smart phone", 699.99, 15);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<Product> products = productController.getAllProducts();

        // Assert
        assertEquals(2, products.size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {
        // Arrange
        when(productService.getProductById(1L)).thenReturn(product1);

        // Act
        ResponseEntity<Product> response = productController.getProductById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        when(productService.createProduct(product1)).thenReturn(product1);

        // Act
        Product createdProduct = productController.createProduct(product1);

        // Assert
        assertEquals(product1, createdProduct);
        verify(productService, times(1)).createProduct(product1);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        when(productService.updateProduct(1L, product1)).thenReturn(product1);

        // Act
        ResponseEntity<Product> response = productController.updateProduct(1L, product1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        doNothing().when(productService).deleteProduct(1L);

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }
}