package com.skillsoft.springboot.controller;

import com.skillsoft.springboot.dto.ProductDTO;
import com.skillsoft.springboot.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDTO> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable("id") String id) {
        return productService.getProduct(id);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody ProductDTO product) {
        productService.addProduct(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO product) {
        productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
    }
}