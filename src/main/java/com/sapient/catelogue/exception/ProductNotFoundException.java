/**
 * 
 */
package com.sapient.catelogue.exception;

/**
 * @author ADMIN
 *
 */
public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}
