package com.AHAtrading.ahatrue.model;


import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Table(name="TECHNOLOGIE_CISELNIK")
@Getter @Setter
public class TechnologieCiselnik {
	
	@Id
	@Hidden
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@ReadOnly
	int id;
	
	@Column(length=5,name="ZKRATKA")
	@Required
	String shortName;//to-do
	
	@Column(length=20,name="POPIS")
	@Required
	String description;//to-do
	
	 @ManyToMany
	 
	 private Set<Demand> demands = new HashSet<>();


}
