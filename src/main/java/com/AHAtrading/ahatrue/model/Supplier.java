package com.AHAtrading.ahatrue.model;

import javax.persistence.*;

import lombok.*;
//https://www.javatpoint.com/hibernate-table-per-subclass-using-annotation-tutorial-example
@Entity
@Table(name="DODAVATEL")
@Getter @Setter
@PrimaryKeyJoinColumn(name="ID")  
public class Supplier extends Company {
	

	
	@Column(length=255,name="POZN2")
	String remark2;
	

}
