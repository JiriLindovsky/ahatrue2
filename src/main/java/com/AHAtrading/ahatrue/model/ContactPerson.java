package com.AHAtrading.ahatrue.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
//https://www.javatpoint.com/hibernate-table-per-subclass-using-annotation-tutorial-example
@Entity
@Table(name="KONTAKTNI_OSOBA")
@Getter @Setter
@PrimaryKeyJoinColumn(name="ID")  
public class ContactPerson extends Person {
	
	@Column(length=30,name="EMAIL")
	@Required
	String email;
	@Column(length=30,name="TEL")
	@Required
	String phoneNumber;
	@Column(length=255,name="POZN")
	String remark;
	

}
