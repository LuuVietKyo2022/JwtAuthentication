package com.example.demo.entites;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductApi {
	@Autowired 
	ProductRepository repo;
	
	@GetMapping
	public List<Product> list(){
		return repo.findAll();
	}
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product){
		 Product savedProduct = repo.save(product);
		 URI productUri = URI.create("/products/" +savedProduct.getId());
		 return ResponseEntity.created(productUri).body(savedProduct);
	}
}
