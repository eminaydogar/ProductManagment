package com.project.WS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Product;
import com.project.service.ProductService;

@RestController
public class AjaxController {

	@Autowired
	private ProductService productService;
	private static Logger log = LoggerFactory.getLogger(AjaxController.class);

	@PutMapping(value = "/updateProduct")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
		System.out.println("----------------------");
		System.out.println("Getting update request from ajax!"+product);
		log.debug("Getting update request from ajax!");
		Product controlProduct = null;
		controlProduct = productService.updateProduct(product);
		if (controlProduct != null) {
			System.out.println("DATA UPDATED.");
			return new ResponseEntity<Object>(new Response<Product>("success", product), HttpStatus.OK);
		}
		return new ResponseEntity<Object>(new Response<Product>("false", null), HttpStatus.BAD_REQUEST);
	}

}
