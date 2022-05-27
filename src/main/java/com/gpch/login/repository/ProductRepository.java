package com.gpch.login.repository;

import com.gpch.login.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByName(String name);
}
