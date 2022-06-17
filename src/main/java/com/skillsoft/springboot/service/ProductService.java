package com.skillsoft.springboot.service;

import com.skillsoft.springboot.dto.ProductDTO;
import com.skillsoft.springboot.entity.ProductEntity;
import com.skillsoft.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<ProductEntity> productEntities = new ArrayList<>();
        productRepository.findAll().forEach(productEntities::add);
        productEntities.stream().forEach(
                p -> productDTOList.add(new ProductDTO(p.getId(), p.getName(), p.getCategory())));
        return productDTOList;
    }

    public ProductDTO getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        return new ProductDTO(productEntity.getId(),
                productEntity.getName(),
                productEntity.getCategory());
    }

    public void addProduct(ProductDTO product) {
        productRepository.save(new ProductEntity(
                product.getName(),
                product.getCategory()));
    }

    public void updateProduct(ProductDTO product) {
        if (productRepository.findById(product.getId()).get() != null) {
            productRepository.save(new ProductEntity(
                    product.getId(),
                    product.getName(),
                    product.getCategory()
            ));
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
