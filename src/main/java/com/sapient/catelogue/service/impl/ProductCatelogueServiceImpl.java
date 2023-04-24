/**
 * 
 */
package com.sapient.catelogue.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.catelogue.dto.ProductDTO;
import com.sapient.catelogue.exception.InvalidProductException;
import com.sapient.catelogue.exception.ProductNotFoundException;
import com.sapient.catelogue.model.Product;
import com.sapient.catelogue.repository.ProductRepository;
import com.sapient.catelogue.service.ProductCatelogueService;

/**
 * @author ADMIN
 *
 */
@Service
public class ProductCatelogueServiceImpl implements ProductCatelogueService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getProductById(Integer id) throws ProductNotFoundException {
		Optional<Product> product = productRepository.findById(id);
		return product.orElseThrow(() -> new ProductNotFoundException("Product not found with the id: " + id));
	}

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product addProduct(ProductDTO productDto) throws InvalidProductException {	
		Product product = new Product();
		product.setName(productDto.getName());
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Integer id) throws ProductNotFoundException {
		Product product = getProductById(id);
		productRepository.delete(product);
	}

	@Override
	public Product updateProduct(Integer id, ProductDTO productDto) throws InvalidProductException, ProductNotFoundException {
		Product product = getProductById(id);
		product.setName(productDto.getName());
		// we can maintain idempotency here
		return productRepository.save(product);
	}

}
