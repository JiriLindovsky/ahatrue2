package com.AHAtrading.ahatrue.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Table(name="FUNKCE")
@Getter @Setter
public class Activity {
    //JL: funkce ve firmě dle dovedností
    /*@Id @Column(length=32)// @DefaultValueCalculator(CurrentDateCalculator.class)
    private String id;*/
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@ReadOnly
	@Hidden
	int id;
	
	@Column(length=5, name="ZKRATKA")
	@Required
	private String shortName;
	 
    @Column(length=100, name="POPIS")
    @Required
    private String name;
    
    @ManyToMany
    private Set<Employee> employees = new HashSet<>();

    // getters and setters
    
}