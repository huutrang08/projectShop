package com.shop.ex.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shop.ex.domain.CartItem;
import com.shop.ex.domain.Customer;

public interface CartItemServices {

	<S extends CartItem> List<S> findAll(Example<S> example, Sort sort);

	<S extends CartItem> List<S> findAll(Example<S> example);

	CartItem getById(Integer id);

	void deleteAll();

	void delete(CartItem entity);

	void deleteById(Integer id);

	long count();

	<S extends CartItem> long count(Example<S> example);

	<S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable);

	boolean existsById(Integer id);

	void flush();

	Optional<CartItem> findById(Integer id);

	List<CartItem> findAllById(Iterable<Integer> ids);

	List<CartItem> findAll(Sort sort);

	List<CartItem> findAll();

	Page<CartItem> findAll(Pageable pageable);

	<S extends CartItem> S save(S entity);

	void update(int id, int quantity);

	List<CartItem> findByCustomer(Customer customer);

	void add(CartItem cartItem, Map<Long, CartItem> map, Long id);

}
