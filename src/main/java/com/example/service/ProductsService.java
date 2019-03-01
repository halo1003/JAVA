package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Product;
import com.example.repository.ProductsRepository;

@Service
public class ProductsService {
	@Autowired
	private ProductsRepository proRepo;
	
	public List<Product> getListProducts(){
		return proRepo.findAll();
	}
	
	public Optional<Product> findEmployeeByID(int product_id) {
		return proRepo.findById(product_id);
	}
}
