package com.shop.ex.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
public class CartItem implements Serializable{
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private int quantity;
	
	@ManyToOne
    @JoinColumn(name =  "customerId",nullable = false)
    private Customer customer;
    
	@ManyToOne
    @JoinColumn(name = "productId",nullable = false)
    private Product product;
    
    
    
	public CartItem(int id, int quantity, Product product, Customer customer) {
		this.id = id;
		this.quantity = quantity;
		this.product = product;
		this.customer = customer;
	}
	public CartItem() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
}
