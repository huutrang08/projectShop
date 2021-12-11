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
@Table(name = "oders")
public class Oder implements Serializable {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int oderId;
   @Temporal(TemporalType.DATE)
   private Date date;
   @Column(nullable = false)
   private double amout;
   @Column(nullable = false)
   private short status;
   
   @ManyToOne
   @JoinColumn(name = "customerId")
   private Customer customer;
   
   @OneToMany(mappedBy = "oder",cascade = CascadeType.ALL)
   private Set<OderDetail> oderDetails;
   
}
