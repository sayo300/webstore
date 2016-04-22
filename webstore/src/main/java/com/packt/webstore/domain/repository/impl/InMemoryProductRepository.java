package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	private List<Product> listOfProducts = new ArrayList<Product>();

	public InMemoryProductRepository() {
		//System.out.println("tu");
		Product iphone = new Product("P1234", "iPhone4", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 4s");
		iphone.setCategory("SmartPhone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);

		Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		laptop_dell.setDescription("DEll laptop, 14 calowy");
		laptop_dell.setCategory("Laptop");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(1000);
		
		Product tablet_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
		tablet_Nexus.setDescription("Google Nexus 7, 7 calowy tablet");
		tablet_Nexus.setCategory("Tablet");
		tablet_Nexus.setManufacturer("Google");
		tablet_Nexus.setUnitsInStock(1000);
		
		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_Nexus);
	}

	public List<Product> getAllProducts() {
		return listOfProducts;
	}

	public Product getProductById(String productId) {
		Product productById = null;
		for(Product product : listOfProducts){
			if( product != null && product.getProductId() != null &&product.getProductId().equalsIgnoreCase(productId)){ //
				
				productById = product;
				break;
			}
			
		}
		if(productById == null){
			throw new ProductNotFoundException(productId);
		}
		return productById;
	}

	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		List<Product> productsByCategory = new ArrayList<Product>();
		for(Product product: listOfProducts){
			if(category.equalsIgnoreCase(product.getCategory())){
			productsByCategory.add(product);
			}
		}
		
		return productsByCategory;
	}

	public Set<Product> getProductByFilter(Map<String, List<String>> filterParams) {
		// TODO Auto-generated method stub
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();
		Set<String> criterias = filterParams.keySet();
		if(criterias.contains("brand")){
			for(String brandName: filterParams.get("brand")){
				for(Product product : listOfProducts){
					if(brandName.equalsIgnoreCase(product.getManufacturer())){
						productsByBrand.add(product);
					}
				}
				
			}
		}
		if(criterias.contains("category")){
			for(String category: filterParams.get("category")){
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}
		productsByCategory.retainAll(productsByBrand);
		return productsByCategory;
	}

	public List<Product> getProductsByManufacturer(String manufacturer) {
		// TODO Auto-generated method stub
		List<Product> productsByManufacture = new ArrayList<Product>();
		for(Product product: listOfProducts){
			if(product.getManufacturer().equalsIgnoreCase(manufacturer)){
				productsByManufacture.add(product);
			}
		}
		
		return productsByManufacture;
	}

	public Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Set<Product> productByPrice = new HashSet<Product>();
		Set<Product> productByHighPrice = new HashSet<Product>();
		Set<String> criterias = filterParams.keySet();
		if(criterias.contains("low")){
			for(String low : filterParams.get("low")){
				System.out.println(low+"  nisska cenna");
				for(Product product: listOfProducts){
					int pom = product.getUnitPrice().compareTo( new BigDecimal(low));
				if(	pom == 1 || pom ==0 ){
				
					productByPrice.add(product);	
					}
				}
			}
		}
		if(criterias.contains("high")){
			for(String high : filterParams.get("high")){
				System.out.println(high+"  wysoka cenna");
				for(Product product : productByPrice){
					int pom = product.getUnitPrice().compareTo(new BigDecimal(high));
					if(pom == -1 || pom ==0){
						productByHighPrice.add(product);
					}
				}
			}
		}
		
		return productByHighPrice;
	}

	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		listOfProducts.add(product);
		
	}


}
