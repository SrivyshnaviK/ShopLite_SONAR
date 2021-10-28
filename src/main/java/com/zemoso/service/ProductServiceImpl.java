package com.zemoso.service;

import com.zemoso.entity.Product;
import com.zemoso.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    @Autowired
    ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public int findUserIdById(int id) {
        return productRepository.findUserIdById(id);
    }


    @Override
    @Transactional
    public void updateStatus(int id) {
        productRepository.updateStatus(id);
    }

    @Override
    @Transactional
    public Product findProductsByProductsId(int i) {
        return productRepository.findProductsByProductsId(i);
    }

    @Override
    @Transactional
    public List<Product> findByStatus() {
        return productRepository.findByStatus();
    }

    @Override
    public List<Integer> findProductsIdByUserId(int id) {
        return productRepository.findProductsIdByUserId(id);
    }

}
