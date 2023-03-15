package com.AHAtrading.ahatrue.model;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Embeddable @Getter @Setter
public class NonstandardDemandItem {
	
	@Column(length=255,name="POPIS")
	String description;
	
	@Column(name="MJ")
	String unit;
	
	@Column(name="MNOZSTVI")
    float quantity;
	
	 @Money // The price property is used to store money
	 BigDecimal price;  // BigDecimal is typically used for money

}
