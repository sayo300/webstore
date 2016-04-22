package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	private List<Customer> listOfCustomer = new ArrayList<Customer>();
	
	public InMemoryCustomerRepository(){
		
		Customer klient1 = new Customer("P01", "Janek");
		klient1.setAddress("Kobielska 7");
		klient1.setNoOfOrdersMade("blablabla");
		
		Customer klient2 = new Customer("P02", "Marek");
		klient1.setAddress("Kobielska 2");
		klient1.setNoOfOrdersMade("blablabla");

		Customer klient3 = new Customer("P03", "Darek");
		klient1.setAddress("Kobielska 3");
		klient1.setNoOfOrdersMade("blablabla");
		
		
		listOfCustomer.add(klient1);
		listOfCustomer.add(klient2);
		listOfCustomer.add(klient3);
		
	}
	
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return listOfCustomer;
	}

}
