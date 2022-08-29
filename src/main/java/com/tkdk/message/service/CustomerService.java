package com.tkdk.message.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tkdk.message.entity.Customer;
import com.tkdk.message.repo.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
/*	public int findLastCustomerId() {		
		Customer customer = customerRepository.findLastCustomerByCustomerId();
		return customer.getCustomerId();
	}
	
	public List<Customer> get100Customer(int lastSentId) {	
		List<Customer> customers = new ArrayList<>();
		customers = customerRepository.find100CustomersByCustomerId(lastSentId);
		return customers;
	} */

	public Optional<Customer> getNextcustomer(int nextId) {		
		Optional<Customer> customer = customerRepository.findById((nextId));
		return customer;
	}

}
