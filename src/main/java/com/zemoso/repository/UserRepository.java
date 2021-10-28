package com.zemoso.repository;

import com.zemoso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u.password from User u where u.id=:id")
    String findPasswordById(@Param("id") int id);
}

