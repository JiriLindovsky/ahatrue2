package com.AHAtrading.ahatrue.model;

import javax.persistence.*;

import lombok.*;
import org.openxava.annotations.ListProperties;

import java.util.Collection;

//https://www.javatpoint.com/hibernate-table-per-subclass-using-annotation-tutorial-example
@Entity
@Table(name="DODAVATEL")
@Getter @Setter
@PrimaryKeyJoinColumn(name="ID")  
public class Supplier extends Company {

	/**JL:added Supplier upon book instead of Author
	 *
 	 */

	@OneToMany(mappedBy="supplier")
	@ListProperties("number, description, price")
	private Collection<Product> products;

	@Column(length=255,name="POZN2")
	String remark2;
	

}
