package com.zemoso.repository;

import com.zemoso.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
    @Query("select o.pid from Ordered o where o.uid=:id")
    List<Integer> findProductsByUserId(@Param("id") int id);
}
