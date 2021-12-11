package com.shop.ex.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginDTO{
	@NotEmpty
   private String username;
	@NotEmpty
    private String Password;
	private Boolean remember = false;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Boolean getRemember() {
		return remember;
	}
	public void setRemember(Boolean remember) {
		this.remember = remember;
	}
	
}
