package com.shop.ex.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;
	@Column(columnDefinition = "nvarchar(100) not null")
  private String name;
	@Column(nullable = false)
  private int quantity;
	@Column(nullable = false)
  private double unitPrice;
	@Column(length = 200)
  private String image;
	@Column(columnDefinition = "nvarchar(200) not null")
  private String description;
	@Column(nullable = false)
  private double discount;
	@Temporal(TemporalType.DATE)
  private Date date;
	@Column(nullable = false)
  private short status;
	
	@ManyToOne
	@JoinColumn(name = "categotyId")
	private Category category;
	
	@OneToMany(mappedBy = "product", cascade =CascadeType.ALL)
	private Set<OderDetail> oderDetails;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<OderDetail> getOderDetails() {
		return oderDetails;
	}

	public void setOderDetails(Set<OderDetail> oderDetails) {
		this.oderDetails = oderDetails;
	}
}
