package com.tkdk.message.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tkdk.message.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

/*	@Query(value = "SELECT c FROM customer c ORDER BY c.customer_id DESC",
		   countQuery = "SELECT COUNT(1) FROM customer",
		   nativeQuery = true)
	Customer findLastCustomerByCustomerId();
	
	
	@Query(value = "SELECT c FROM customer c WHERE c.customer_id>=:lastSentId ORDER BY c.customer_id",
		   countQuery = "SELECT COUNT(100) FROM customer",
		   nativeQuery = true)
	List<Customer> find100CustomersByCustomerId(@Param("lastSentId") int lastSentId); */

}
