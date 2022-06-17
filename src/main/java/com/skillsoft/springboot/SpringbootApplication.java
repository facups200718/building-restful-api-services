package com.skillsoft.springboot;

import com.skillsoft.springboot.entity.ProductEntity;
import com.skillsoft.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootApplication implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new ProductEntity("Television", "Electronics"));
        productRepository.save(new ProductEntity("Air Conditioner", "Electronics"));
        productRepository.save(new ProductEntity("Sofa", "Furniture"));
        productRepository.save(new ProductEntity("Cushions", "Home Essentials"));
        productRepository.save(new ProductEntity("Wardrobe", "Furniture"));
    }
}
