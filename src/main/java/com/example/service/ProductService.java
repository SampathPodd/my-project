package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Retrieve all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Retrieve a product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // Update an existing product
    public Product updateProduct(String id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            if (updatedProduct.getName() != null) product.setName(updatedProduct.getName());
            if (updatedProduct.getDescription() != null) product.setDescription(updatedProduct.getDescription());
            if (updatedProduct.getPrice() != null) product.setPrice(updatedProduct.getPrice());
            if (updatedProduct.getQuantity() != null) product.setQuantity(updatedProduct.getQuantity());
            if (updatedProduct.getCategory() != null) product.setCategory(updatedProduct.getCategory());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    // Delete a product by ID
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }
}