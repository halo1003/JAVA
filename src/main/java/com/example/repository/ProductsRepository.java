package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer>{

}
