package com.project.dao;

import java.util.List;

import com.project.model.Product;

public interface ProductDao {

	public List<Product> getAllProducts();

	public Product getProduct(long id);

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProduct(long id);

}
