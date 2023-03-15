package com.AHAtrading.ahatrue.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "FIRMA")
@Getter @Setter
@View(name="Simple", // This view is used only when “Simple” is specified
members="id, name; fullName; remark" // Shows only number and name in the same line
)
@View(name="InLine", // This view is used only when “Simple” is specified
members="id, name, fullName, remark" // Shows only number and name in the same line
)
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@ReadOnly
	@Hidden
	int id;
	
	@Column(length=15,name="NAZEV")
	@Required
	String name;//nick
	
	@Column(length=50,name="PLNY_NAZEV")
	@Required
	String fullName;
	@Column(length=255,name="POZN")
	String remark;
	
	 @Embedded // This is the way to reference an embeddable class
	    Address address; // A regular Java reference
	

}
