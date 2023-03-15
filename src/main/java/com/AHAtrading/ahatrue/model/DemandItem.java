package com.AHAtrading.ahatrue.model;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "POLOZKA_POPTAVKY")
@Getter @Setter
@View(name="Simple", // This view is used only when “Simple” is specified
members="id, name; fullName; remark" // Shows only number and name in the same line
)
public class DemandItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	@ReadOnly
	@Hidden
	int id;
	
	 @ManyToOne( // The reference is persisted as a database relationship
	            fetch=FetchType.LAZY, // The reference is loaded on demand
	            optional=true) // The reference can have no value
	    @DescriptionsList // Thus the reference is displayed using a combo
	    TypPolozkyPoptavky typPolozkyPoptavky; // A regular Java reference
}