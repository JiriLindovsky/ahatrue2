package com.AHAtrading.ahatrue.model;
 
import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Entity  // This marks Customer class as an entity
@Getter @Setter // This makes all fields below publicly accessible
@PrimaryKeyJoinColumn(name="ID")
@View(name="Simple",
		members="id, name"  )

public class Customer extends Company {
 

	@Column(length=255,name="POZN2")
	String remark2;
	 @ManyToOne( // The reference is persisted as a database relationship
	            fetch=FetchType.LAZY, // The reference is loaded on demand
	            optional=true) // The reference can have no value
	    @DescriptionsList // Thus the reference is displayed using a combo
	    TypZakaznikaCiselnik typZakaznikaCiselnik; // A regular Java reference
	 
}