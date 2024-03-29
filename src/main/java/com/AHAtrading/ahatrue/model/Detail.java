package com.AHAtrading.ahatrue.model;

import java.math.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import com.AHAtrading.ahatrue.calculators.*;

@Entity
@View(members="product; quantity, pricePerUnit, amount")
public class Detail extends Identifiable {
	
	@ManyToOne
	private CommercialDocument parent; 
	
	private int quantity;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	@ReferenceView("Simple")
	@NoFrame
	private Product product;
	
	@DefaultValueCalculator(
		value=PricePerUnitCalculator.class,
		properties=@PropertyValue(
			name="productNumber",
			from="product.number")
	)
	@Stereotype("MONEY")
	private BigDecimal pricePerUnit;
	
	@PrePersist
	private void onPersist() {
		getParent().getDetails().add(this);
		getParent().recalculateAmount();  
	}
		
	@PreUpdate
	private void onUpdate() {
		getParent().recalculateAmount();
	}	
		
	@PreRemove
	private void onRemove() {
		if (getParent().isRemoving()) return;
		getParent().getDetails().remove(this);
		getParent().recalculateAmount();
	}
				
	@Stereotype("MONEY")  
	@Depends("pricePerUnit, quantity")
	public BigDecimal getAmount() {
		return new BigDecimal(quantity)
			.multiply(getPricePerUnit());
	}
		
	// Getters and setters
	public CommercialDocument getParent() {
		return parent;
	}

	public void setParent(CommercialDocument parent) {
		this.parent = parent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit==null?
			BigDecimal.ZERO:pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

}
