package com.shop.ex.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.ex.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findByEmailContaining(String email);

}
