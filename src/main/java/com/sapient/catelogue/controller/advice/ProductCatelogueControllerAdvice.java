/**
 * 
 */
package com.sapient.catelogue.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sapient.catelogue.exception.ProductNotFoundException;

/**
 * @author ADMIN
 *
 */
@ControllerAdvice
public class ProductCatelogueControllerAdvice {
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
		
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleException(Exception exception) {
		
	}

}
