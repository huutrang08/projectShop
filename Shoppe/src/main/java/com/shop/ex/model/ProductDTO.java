package com.shop.ex.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO{
  private Long productId;
  private String name;
  private int quantity;
  private double unitPrice;
  private String image;
  private MultipartFile imageFile;
  private String description;
  private double discount;
  private Date date;
  private short status;
  private Long categotyId;
  private Boolean isEdit = false;
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
public MultipartFile getImageFile() {
	return imageFile;
}
public void setImageFile(MultipartFile imageFile) {
	this.imageFile = imageFile;
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
public Long getCategotyId() {
	return categotyId;
}
public void setCategotyId(Long categotyId) {
	this.categotyId = categotyId;
}
public Boolean getIsEdit() {
	return isEdit;
}
public void setIsEdit(Boolean isEdit) {
	this.isEdit = isEdit;
}

}
