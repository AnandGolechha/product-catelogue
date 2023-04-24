/**
 * 
 */
package com.sapient.catelogue.service;

import java.util.List;

import com.sapient.catelogue.dto.ProductDTO;
import com.sapient.catelogue.exception.InvalidProductException;
import com.sapient.catelogue.exception.ProductNotFoundException;
import com.sapient.catelogue.model.Product;

/**
 * @author ADMIN
 *
 */
public interface ProductCatelogueService {

	public Product getProductById(Integer id) throws ProductNotFoundException;
	
	public List<Product> getProducts();
	
	public Product addProduct(ProductDTO productDto) throws InvalidProductException;
	
	public void deleteProduct(Integer id) throws ProductNotFoundException;
	
	public Product updateProduct(Integer id, ProductDTO productDto) throws InvalidProductException, ProductNotFoundException;
}
