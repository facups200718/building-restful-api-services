package com.skillsoft.springboot.exception;

import java.io.Serializable;

public class ProductNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public ProductNotFoundException(Long id) {
        super("The product with " + id + " cannot be found!");
    }
}
