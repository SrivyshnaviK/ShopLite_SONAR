package com.zemoso.service;

import com.zemoso.entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(int id);
    void save(Product product);
    void deleteById(int id);
    int findUserIdById(int id);
    void updateStatus(int id);
    Product findProductsByProductsId(int i);
    List<Product> findByStatus();
    List<Integer> findProductsIdByUserId(int id);
}
