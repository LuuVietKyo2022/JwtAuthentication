package com.example.demo.RestAPI;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//@PermitAll
	@PreAuthorize("hasAuthority('guest') or hasAuthority('user') or hasAuthority('admin')")
	@GetMapping("/guest/getListProduct")
	public List<Product> list(){
		return repo.findAll();
	}
	
	@PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
	@PostMapping("/user/createNewProduct")
	public ResponseEntity<Product> create(@RequestBody Product product){
		 Product savedProduct = repo.save(product);
		 URI productUri = URI.create("/products/" +savedProduct.getId());
		 return ResponseEntity.created(productUri).body(savedProduct);
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/admin/deleteProduct/{id}")
	public String delete(@PathVariable Integer id){
		 Optional<Product> Product=repo.findById(id);
		if(Product == null) {
			return "Don't have product with this id";
		}
		repo.deleteById(id);
		 return "delete success";
	}
}