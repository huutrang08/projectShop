package com.shop.ex.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.ex.domain.CartItem;
import com.shop.ex.domain.Customer;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
	List<CartItem> findByCustomer(Customer customer);
}
