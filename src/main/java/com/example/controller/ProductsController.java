package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.service.ProductsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductsController {
	@Autowired
	private ProductsService proService;

	// Get all PRODUCT in database
	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
	public List<Product> getAllLocation() {
		return proService.getListProducts();
	}

	// Find PRODUCT by ID
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findEmployeeByID(@PathVariable("id") int product_id) {
		Object result;
		HttpStatus status;

		Optional<Product> product = proService.findEmployeeByID(product_id);

		if (product.isPresent()) {
			result = product.get();
			status = HttpStatus.OK;
		} else {
			result = "find employee fail!!!";
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<Object>(result, status);
	}
}
