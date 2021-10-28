package com.zemoso.service;

import com.zemoso.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    User save(User user);
    void deleteById(int id);
    String findPasswordById(int id);
    Page<User> findPaginated(int pageNo,int pageSize);
}
