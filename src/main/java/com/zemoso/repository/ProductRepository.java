package com.zemoso.repository;

import com.zemoso.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select p.user.id from Product p where p.id=:id")
    Integer findUserIdById(@Param("id") int id);
    @Query("select p.id from Product p where p.name=:name")
    Integer findIdByProductName(@Param("name") String name);
    @Modifying
    @Query("update Product p set p.available=false where p.id=:id")
    void updateStatus(@Param("id") int id);
    @Query("select p from Product p where p.id=:id")
    Product findProductsByProductsId(@Param("id") int id);
    @Query("select p from Product p where p.available=true")
    List<Product> findByStatus();
    @Query("select p.id from Product p where p.user.id=:id")
    List<Integer> findProductsIdByUserId(@Param("id") int id);
}
