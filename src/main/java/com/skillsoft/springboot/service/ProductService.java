package com.skillsoft.springboot.service;

import com.skillsoft.springboot.dto.ProductDTO;
import com.skillsoft.springboot.entity.ProductEntity;
import com.skillsoft.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable("productDTOList")
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<ProductEntity> productEntities = new ArrayList<>();
        try { //Esto lo hacemos para ver la diferencia de tiempo que hay cuando usamos el cache
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        productRepository.findAll().forEach(productEntities::add);
        productEntities.stream().forEach(
                p -> productDTOList.add(new ProductDTO(p.getId(), p.getName(), p.getCategory())));
        return productDTOList;
    }
    //Se usa la key para que no nos traiga siempre la info del mismo producto. Esta indica que el parametro diferenciador es el #0 osea el id del producto
    @Cacheable(value = "productDTO", key = "#p0")
    public ProductDTO getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        ProductDTO productDTO = new ProductDTO(productEntity.getId(),
                productEntity.getName(),
                productEntity.getCategory());
        return productDTO;
    }

    //Con esto limpiamos el cache de "products" cada vez que agregamos un producto asi se actualiza
    @CacheEvict(value = "products", allEntries = true)
    public void addProduct(ProductDTO product) {
        productRepository.save(new ProductEntity(
                product.getName(),
                product.getCategory()));
    }

    //Decimos que cachee despues de limpiar el cache
    @Caching(evict = {
            @CacheEvict(value = "product", key = "#p0"),
            @CacheEvict(value = "products", allEntries = true)
    })
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
