package com.zemoso.service;

import com.zemoso.entity.Ordered;

import java.util.List;
import java.util.Optional;

public interface OrderedService {
    List<Ordered> findAll();
    Optional<Ordered> findById(int id);
    void save(Ordered ordered);
    List<Integer> findProductsByUserId(int id);
}
