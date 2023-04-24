/**
 * 
 */
package com.sapient.catelogue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.catelogue.dto.ProductDTO;
import com.sapient.catelogue.exception.InvalidProductException;
import com.sapient.catelogue.exception.ProductNotFoundException;
import com.sapient.catelogue.model.Product;
import com.sapient.catelogue.service.ProductCatelogueService;

/**
 * @author ADMIN
 *
 */
@RestController
@RequestMapping("/api/${api.version}/catelogue")
public class ProductCatelogueController {

	@Autowired
	private ProductCatelogueService productCatelogueService;
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody ProductDTO product) throws InvalidProductException {
		Product savedProduct = productCatelogueService.addProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productCatelogueService.getProducts();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) throws ProductNotFoundException {
		Product product = productCatelogueService.getProductById(id);
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) throws ProductNotFoundException {
		productCatelogueService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody ProductDTO product) throws InvalidProductException, ProductNotFoundException {
		Product updatedProduct = productCatelogueService.updateProduct(id, product);
		return ResponseEntity.ok(updatedProduct);
	}
}
