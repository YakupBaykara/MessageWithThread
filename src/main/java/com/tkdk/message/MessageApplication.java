package com.tkdk.message;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tkdk.message.entity.Customer;
import com.tkdk.message.repo.CustomerRepository;

@SpringBootApplication
public class MessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}
		
	@Autowired
	private CustomerRepository customerRepository;
	
/*	public void generateDefaultData() {
		Long count = customerRepository.count();
		if (count == 0L) {
			List<Customer> customerList = new ArrayList<>();
			for (int i = 1; i <= 300; i++) {
				customerList.add(
						Customer.builder()
							.name("Customer" +i)
							.email("Customer" +i+ "@gmail.com")
							.build());
			}
			customerRepository.saveAll(customerList);
		} */
	
	@Bean
	CommandLineRunner run(CustomerRepository custmerRepository) {
		
		return args -> {
			Long count = customerRepository.count();
			if (count == 0L) {
				List<Customer> customerList = new ArrayList<>();
				for (int i = 1; i <= 300; i++) {
					customerList.add(
							Customer.builder()
								.name("Customer" +i)
								.email("Customer" +i+ "@gmail.com")
								.build());
				}
				customerRepository.saveAll(customerList);
			}
		};
	}
} 

