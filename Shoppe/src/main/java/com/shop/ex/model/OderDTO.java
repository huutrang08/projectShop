package com.shop.ex.model;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OderDTO{
   private int oderId;
   private Date date;
   private int customerId;
   private double amout;
   private short status;
}
