/**
 * 
 */
package com.sapient.catelogue.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.catelogue.dto.ProductDTO;
import com.sapient.catelogue.exception.InvalidProductException;
import com.sapient.catelogue.exception.ProductNotFoundException;
import com.sapient.catelogue.model.Product;
import com.sapient.catelogue.repository.ProductRepository;

/**
 * @author ADMIN
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCatelogueControllerTest {
	
	@MockBean
	private ProductRepository productRepository;
	
	@Autowired
	private ProductCatelogueController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.sapient.catelogue.controller.ProductCatelogueController#addProduct(com.sapient.catelogue.dto.ProductDTO)}.
	 * @throws InvalidProductException 
	 */
	@Test
	void testAddProduct() throws InvalidProductException {
		ProductDTO productDto = new ProductDTO();
		productDto.setName("test-product");
		
		Product mockedProduct = new Product();
		mockedProduct.setId(1);
		mockedProduct.setName(productDto.getName());
		when(productRepository.save(any())).thenReturn(mockedProduct);
		
		ResponseEntity<Product> response = controller.addProduct(productDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	/**
	 * Test method for {@link com.sapient.catelogue.controller.ProductCatelogueController#getProducts()}.
	 */
	@Test
	void testGetProducts() {
		Product mockedProduct = new Product();
		mockedProduct.setId(1);
		mockedProduct.setName("test-product");
		List<Product> mockedProducts = new ArrayList<>();
		mockedProducts.add(mockedProduct);
		when(productRepository.findAll()).thenReturn(mockedProducts);
		
		ResponseEntity<List<Product>> response = controller.getProducts();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().size() > 0);
		
	}

	/**
	 * Test method for {@link com.sapient.catelogue.controller.ProductCatelogueController#getProduct(java.lang.Integer)}.
	 * @throws ProductNotFoundException 
	 */
	@Test
	void testGetProduct() throws ProductNotFoundException {
		Product mockedProduct = new Product();
		mockedProduct.setId(1);
		mockedProduct.setName("test-product");
		when(productRepository.findById(any())).thenReturn(Optional.of(mockedProduct));
		
		ResponseEntity<Product> response = controller.getProduct(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * Test method for {@link com.sapient.catelogue.controller.ProductCatelogueController#deleteProduct(java.lang.Integer)}.
	 * @throws ProductNotFoundException 
	 */
	@Test
	void testDeleteProduct() throws ProductNotFoundException {
		Product mockedProduct = new Product();
		mockedProduct.setId(1);
		mockedProduct.setName("test-product");
		doNothing().when(productRepository).delete(any());
		when(productRepository.findById(any())).thenReturn(Optional.of(mockedProduct));
		
		ResponseEntity<Void> response = controller.deleteProduct(1);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	/**
	 * Test method for {@link com.sapient.catelogue.controller.ProductCatelogueController#updateProduct(java.lang.Integer, com.sapient.catelogue.dto.ProductDTO)}.
	 * @throws ProductNotFoundException 
	 * @throws InvalidProductException 
	 */
	@Test
	void testUpdateProduct() throws InvalidProductException, ProductNotFoundException {
		
		Product mockedProduct = new Product();
		mockedProduct.setId(1);
		mockedProduct.setName("test-product");
		when(productRepository.findById(any())).thenReturn(Optional.of(mockedProduct));
		
		ProductDTO productDto = new ProductDTO();
		productDto.setName("updated-product");
		mockedProduct.setName(productDto.getName());
		
		when(productRepository.save(any())).thenReturn(mockedProduct);
		
		ResponseEntity<Product> response = controller.updateProduct(1, productDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

}
