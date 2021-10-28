package com.zemoso.service;

import com.zemoso.entity.User;
import com.zemoso.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserServiceImpl() {}

    @Override
    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public User findById(int id) {
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        throw new RuntimeException("User Not Found:"+id);
    }
    @Override
    @Transactional
    public User save(User user) {
        userRepository.save(user);
        return user;
    }
    @Override
    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public String findPasswordById(int id) {
        return userRepository.findPasswordById(id);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
        return this.userRepository.findAll(pageable);
    }
}
