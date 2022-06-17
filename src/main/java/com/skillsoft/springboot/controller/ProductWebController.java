package com.skillsoft.springboot.controller;

import com.skillsoft.springboot.dto.ProductDTO;
import com.skillsoft.springboot.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/update_product/{id}")
    public String editForm(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("product", productController.getProduct(id));
        return "update_product";
    }

    @PostMapping(value = "/save_update")
    public String saveUpdateProduct(@ModelAttribute("product") ProductDTO product) {
        productController.updateProduct(product);
        return "redirect:/";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productController.getProduct(id));
        return "delete_product";
    }

    @PostMapping("/save_delete")
    public String saveDeleteProduct(@ModelAttribute("product") ProductDTO product) {
        productController.deleteProduct(product.getId());
        return "redirect:/";
    }
}
