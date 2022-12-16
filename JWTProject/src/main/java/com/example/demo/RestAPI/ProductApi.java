package com.example.demo.RestAPI;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repositories.ProductRepository;
import com.example.demo.entites.Product;


@RestController

public class ProductApi {

	@Autowired 
	ProductRepository repo;
	
	@PreAuthorize("hasRole('guest') or hasRole('user') or hasRole('admin')")
	@GetMapping("/guest/getListProduct")
	public List<Product> list(){
		return repo.findAll();
	}
	
	@PreAuthorize(" hasRole('user') or hasRole('admin')")
	@PostMapping("/user/createNewProduct")
	public ResponseEntity<Product> create(@RequestBody Product product){
		 Product savedProduct = repo.save(product);
		 URI productUri = URI.create("/products/" +savedProduct.getId());
		 return ResponseEntity.created(productUri).body(savedProduct);
	}
}
