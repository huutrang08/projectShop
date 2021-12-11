package com.shop.ex.services.Impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shop.ex.domain.CartItem;
import com.shop.ex.domain.Customer;
import com.shop.ex.reponsitory.CartItemRepository;
import com.shop.ex.services.CartItemServices;
@Service
public class CartItemServicesImpl implements CartItemServices{
    @Autowired 
	CartItemRepository cartItemRepository;
    
    @Override
	public void update(int id,int quantity) {
    	 Optional<CartItem> optional = findById(id);
    	 optional.get().setQuantity(quantity);
    	 cartItemRepository.save(optional.get());
    }
    
    @Override
	public void add(CartItem cartItem,Map<Long, CartItem> map,Long id) {
    	CartItem cart = map.get(id);
    	if (cart != null) {
			cart.setQuantity(cartItem.getQuantity()+cart.getQuantity());
			cartItemRepository.save(cart);
		}else {
			save(cartItem);
		}
    }
    
	@Override
	public List<CartItem> findByCustomer(Customer customer) {
		return cartItemRepository.findByCustomer(customer);
	}

	@Override
	public <S extends CartItem> S save(S entity) {
		return cartItemRepository.save(entity);
	}

	@Override
	public Page<CartItem> findAll(Pageable pageable) {
		return cartItemRepository.findAll(pageable);
	}

	@Override
	public List<CartItem> findAll() {
		return cartItemRepository.findAll();
	}

	@Override
	public List<CartItem> findAll(Sort sort) {
		return cartItemRepository.findAll(sort);
	}

	@Override
	public List<CartItem> findAllById(Iterable<Integer> ids) {
		return cartItemRepository.findAllById(ids);
	}

	@Override
	public Optional<CartItem> findById(Integer id) {
		return cartItemRepository.findById(id);
	}

	@Override
	public void flush() {
		cartItemRepository.flush();
	}

	@Override
	public boolean existsById(Integer id) {
		return cartItemRepository.existsById(id);
	}

	@Override
	public <S extends CartItem> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cartItemRepository.findAll(example, pageable);
	}

	@Override
	public <S extends CartItem> long count(Example<S> example) {
		return cartItemRepository.count(example);
	}

	@Override
	public long count() {
		return cartItemRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		cartItemRepository.deleteById(id);
	}

	@Override
	public void delete(CartItem entity) {
		cartItemRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		cartItemRepository.deleteAll();
	}

	@Override
	public CartItem getById(Integer id) {
		return cartItemRepository.getById(id);
	}

	@Override
	public <S extends CartItem> List<S> findAll(Example<S> example) {
		return cartItemRepository.findAll(example);
	}

	@Override
	public <S extends CartItem> List<S> findAll(Example<S> example, Sort sort) {
		return cartItemRepository.findAll(example, sort);
	}
    
    
}
