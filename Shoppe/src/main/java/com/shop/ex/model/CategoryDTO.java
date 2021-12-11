package com.shop.ex.model;
import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CategoryDTO implements Serializable{
	
	
   private Long categotyId;
   @NotEmpty
   @Length(min = 5)
   private String name;
   private Boolean isEdit = false;
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
public Boolean getIsEdit() {
	return isEdit;
}
public void setIsEdit(Boolean isEdit) {
	this.isEdit = isEdit;
}
   
   

}
