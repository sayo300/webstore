package com.packt.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.packt.webstore.domain.Product;

public interface ProductService {

	
	List<Product> getAllProducts();
	Product getProductById(String productId);
	List<Product> getProductsBCategory(String category);
	Set<Product> getProductByFilter(Map<String, List<String>> filterParams);
	List<Product> getProductsByManufacture(String manufacture);
	Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams);
	void addProduct(Product product);
}
