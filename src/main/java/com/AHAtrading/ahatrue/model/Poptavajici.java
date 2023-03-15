package com.AHAtrading.ahatrue.model;
 
import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Entity  // This marks Customer class as an entity
@Getter @Setter // This makes all fields below publicly accessible
@PrimaryKeyJoinColumn(name="ID")
@View(name="Simple", // This view is used only when “Simple” is specified
members="id, name; fullName; remark" // Shows only number and name in the same line
)
@View(name="InLine", // This view is used only when “Simple” is specified
members="id, name, fullName, remark" // Shows only number and name in the same line
)
public class Poptavajici extends Company {
 

	@Column(length=255,name="POZN2")
	String remark2;
	 @ManyToOne( // The reference is persisted as a database relationship
	            fetch=FetchType.LAZY, // The reference is loaded on demand
	            optional=true) // The reference can have no value
	    @DescriptionsList // Thus the reference is displayed using a combo
	    TypZakaznikaCiselnik typCiselnik; // A regular Java reference
}