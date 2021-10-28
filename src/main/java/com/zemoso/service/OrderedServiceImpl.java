package com.zemoso.service;

import com.zemoso.entity.Ordered;
import com.zemoso.repository.OrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderedServiceImpl implements OrderedService{
    private OrderedRepository orderedRepository;
    @Autowired
    public OrderedServiceImpl(OrderedRepository orderedRepository){
        this.orderedRepository=orderedRepository;
    }
    @Override
    @Transactional
    public List<Ordered> findAll() {
        return orderedRepository.findAll();
    }
    @Override
    @Transactional
    public Optional<Ordered> findById(int id) {
        return orderedRepository.findById(id);
    }
    @Override
    @Transactional
    public void save(Ordered ordered) {
        orderedRepository.save(ordered);
    }
    @Override
    @Transactional
    public List<Integer> findProductsByUserId(int id) {
        return orderedRepository.findProductsByUserId(id);
    }
}
