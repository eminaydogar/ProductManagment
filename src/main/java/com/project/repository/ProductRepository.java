package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "?1",nativeQuery = true)
	public Product createGenericQuery(String query);
		
	
	
}
