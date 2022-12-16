package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entites.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{

}
