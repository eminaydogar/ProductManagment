package com.project.service;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Product;
import com.project.repository.ProductRepository;

@Service
public class ProductService {

	private static Logger log = LoggerFactory.getLogger(ProductService.class);
	@Autowired
	private ProductRepository productRepository;

	public ProductService() {
		log.info("ProductService.class called");
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProduct(Long id) {
		try {
			Product product = productRepository.findById(id).get();
			return product;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public Product addProduct(Product product) {
		validationControl(product);
		return productRepository.save(product);
	}

	public Product updateProduct(Product product) {
		validationControl(product);
		return productRepository.saveAndFlush(product);
	}

	public void deleteProduct(Long id) {
		Product existingProduct = null;
		existingProduct = productRepository.getOne(id);
		validationControl(existingProduct);
		productRepository.delete(existingProduct);
	}
	
	public Product updateProductWithNQ(String entity,Map<String, String> values,Long id) {
		return productRepository.createGenericQuery(updateNaviteQuery(entity, values, id));
	}

	private String updateNaviteQuery(String entity,Map<String, String> values,Long id) {
		int mapSize = 0;
		StringBuilder sql = new StringBuilder();
		sql.setLength(0);
		sql.append("update " + entity);
		if (values.size() > 0 && values != null) {
			sql.append(" set ");
			for (String key : values.keySet()) {
				mapSize++;
				if (key != "id") {
					sql.append(key + " = '" + values.get(key) + "'");
				}
				if (mapSize == values.size()) {
					break;
				}
				sql.append(",");

			}
			sql.append(" where id = " + id);
		}
		log.info("updateNaviteQuery called.Query:" + sql.toString());
		return sql.toString();
	}

	private void validationControl(Product product) {
		if (product == null) {
			throw new NullPointerException("Entity was null " + product);
		}

		else if (product.getId() == null || product.getCategory() == null || product.getCategoryType() == null
				|| product.getName() == null || product.getStatus() == null || product.getCount() == null
				|| product.getPrice() == null) {
			throw new IllegalArgumentException("Entity was not valid." + product);
		}

	}

}
