package com.AHAtrading.ahatrue.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "OSOBA")
@Getter @Setter
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@ReadOnly
	@Hidden
	int id;
	
	@Column(length=15,name="JMENO")
	@Required
	String firstName;
	
	@Column(length=15,name="PRIJMENI")
	@Required
	String surname;
	
	@Column(length=15,name="POZN")
	String remark;

}
