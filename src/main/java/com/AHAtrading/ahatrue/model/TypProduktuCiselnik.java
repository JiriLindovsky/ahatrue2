package com.AHAtrading.ahatrue.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Table(name="TYP_PRODUKTU_CISELNIK")
@Getter @Setter
public class TypProduktuCiselnik {
	
	@Id
	@Hidden
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@ReadOnly
	int id;
	
	@Column(length=5,name="ZKRATKA")
	@Required
	String shortName;//to-do
	
	@Column(length=20,name="NAZEV")
	@Required
	String description;//to-do

}
