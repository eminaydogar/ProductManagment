package com.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.model.Product;
import com.project.service.ProductService;

@Controller
public class ProductController {

	private static Logger log = LoggerFactory.getLogger(ProductController.class);

	public ProductController() {

		log.info("ProductController was called");
	}

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String Main(Model model) {
		List<Product> productList = productService.getAllProducts();
		model.addAttribute("productModel", productList);
		model.addAttribute("product", new Product());
		return "main";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	String add(@ModelAttribute("product") Product product, BindingResult br) {
		Product existingProduct = productService.getProduct(product.getId());
		if (existingProduct != null) {
			log.warn("Adding data denied.Id is already exist [" + product.getId() + "]");
			br.rejectValue("id", "id_error", "Id is already exist!");
			return "redirect:/";
		} 
		productService.addProduct(product);
		return "redirect:/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	String update(@ModelAttribute("product") Product product) {
		productService.updateProduct(product);
		return "redirect:/";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "deletebyId") Long id) {
		if (id == null) {
			return "forward:/";
		}
		productService.deleteProduct(id);
		return "redirect:/";

	}

}
