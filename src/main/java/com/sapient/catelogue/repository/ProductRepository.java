/**
 * 
 */
package com.sapient.catelogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.catelogue.model.Product;

/**
 * @author ADMIN
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
