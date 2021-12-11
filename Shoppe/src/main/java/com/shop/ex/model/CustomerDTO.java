package com.shop.ex.model;

import java.sql.Date;

public class CustomerDTO {
	public CustomerDTO() {
	}
   public CustomerDTO(int customerId, String name, String email, String password, String phone, Date date,
			short status) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.Password = password;
		this.phone = phone;
		this.date = date;
		this.status = status;
	}
private int customerId;
   private String name;
   private String email;
   private String Password;
   private String phone;
   public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Date getdate() {
	return date;
}
public void setdate(Date date) {
	this.date = date;
}
public short getStatus() {
	return status;
}
public void setStatus(short status) {
	this.status = status;
}
private Date date;
   private short status;
}
