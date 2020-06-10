package com.codegym.service;

import com.codegym.model.Product;

public interface ProductService extends GeneralService<Product> {
    void save(Product product);
    void remove(Long id);
}
