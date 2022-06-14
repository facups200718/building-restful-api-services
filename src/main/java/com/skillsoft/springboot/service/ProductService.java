package com.skillsoft.springboot.service;

import com.skillsoft.springboot.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    private List<ProductDTO> products = new ArrayList<>(Arrays.asList(
            new ProductDTO("P101", "Monitor", "Electronics"),
            new ProductDTO("P102", "Blanket", "Household"),
            new ProductDTO("P103", "Laptop", "Electronics"),
            new ProductDTO("P104", "Shirt", "Fashion"),
            new ProductDTO("P105", "Pens", "School")
    ));

    public List<ProductDTO> getProducts() {
        return products;
    }

    public ProductDTO getProduct(String id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst().get();
    }

    public void addProduct(ProductDTO product) {
        products.add(product);
    }
}
