package com.skillsoft.springboot.controller;

import com.skillsoft.springboot.dto.ProductDTO;
import com.skillsoft.springboot.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductWebController {
    @Autowired
    ProductController productController;

    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productController.getAllProducts());
        return "list_products";
    }

    @GetMapping("/new_product")
    public String addProduct(Model model) {
        model.addAttribute("product", new ProductEntity());
        return "new_product";
    }

    @PostMapping(value = "/save_new")
    public String saveNewProduct(@ModelAttribute("product") ProductDTO product) {
        productController.addProduct(product);
        return "redirect:/";
    }
}
