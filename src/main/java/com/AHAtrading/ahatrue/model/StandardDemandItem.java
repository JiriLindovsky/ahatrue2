package com.AHAtrading.ahatrue.model;

import javax.persistence.*;

import lombok.*;

@Embeddable @Getter @Setter
public class StandardDemandItem {
	
	  float quantity;
	  
	    @ManyToOne(fetch = FetchType.LAZY, optional = true)
	    StandardProduct standardProduct;
	
	
	

}
