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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int customerId;
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
	public Set<Oder> getOders() {
		return oders;
	}
	public void setOders(Set<Oder> oders) {
		this.oders = oders;
	}
	@Column(columnDefinition = "nvarchar(50) not null")
   private String name;
	@Column(columnDefinition = "nvarchar(100) not null")
   private String email;
	@Column(length = 60)
   private String Password;
	@Column(length = 60)
   private String phone;
	@Temporal(TemporalType.DATE)
   private Date date;
	@Column(nullable = false)
   private short status;
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private Set<Oder> oders;
}
