package com.shop.ex.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Category implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long categotyId;
	@Column(name = "categoryName",length = 100,
			columnDefinition = "nvarchar(100) not null")
   private String name;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> products;

	public Long getCategotyId() {
		return categotyId;
	}

	public void setCategotyId(Long categotyId) {
		this.categotyId = categotyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
