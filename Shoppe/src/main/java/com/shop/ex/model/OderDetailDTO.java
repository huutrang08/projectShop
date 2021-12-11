package com.shop.ex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OderDetailDTO {
	private int orderDetailId;
	private int oderId;
	private int productId;
	private int quantity;
	private double unitPrice;
    
    
}
