package com.AHAtrading.ahatrue.model;
 
import java.time.*;
import java.util.*;

import javax.persistence.*;

//import org.hibernate.annotations.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

import com.AHAtrading.ahatrue.calculators.*;

import lombok.*;
 
@Entity @Getter @Setter
@Table(name="POPTAVKA")
@View(members= // This view has no name, so it will be the view used by default
"year, number, date;" + // Comma separated means in the same line
"poptavajici;" + // Semicolon means a new line
"technologies;" +
"specifikace;"+
"nonstandardDemandItems, standardDemandItems;" +
"photos;" +
"remarks"



)
public class Demand {

    @Id
   // @GeneratedValue(generator="system-uuid")
    @Hidden
    @GeneratedValue(strategy=GenerationType.AUTO)
    //@GenericGenerator(name="system-uuid", strategy="uuid")
    @Column(length=32)
    String oid;//JL:primary key

    @Column(length=4,name="rok")
    //@LabelFormat(LabelFormatType.NO_LABEL)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Current year
    int year;//JL:rok
 
    @Column(length=6,name="CISLO_POPTAVKY")
    @DefaultValueCalculator(value=NextNumberForYearCalculator.class,
    properties=@PropertyValue(name="year") // To inject the value of year from Invoice to
                                               // the calculator before calling to calculate()
    )
    int number;//JL:§.Číslo poptávky
 

    
    @Required
    @DefaultValueCalculator(CurrentLocalDateCalculator.class) // Current date
    LocalDate date;//JL:§.Datum poptání
 
    @ManyToOne(fetch=FetchType.LAZY, optional=false) // Customer is required
    @ReferenceView("InLine") // The view named 'Simple' is used to display this reference
    Poptavajici poptavajici;
    
    /*@ManyToOne( // The reference is persisted as a database relationship
            fetch=FetchType.LAZY, // The reference is loaded on demand
            optional=true) // The reference can have no value
    @DescriptionsList // Thus the reference is displayed using a combo
    TechnologieCiselnik technologieCiselnik; // A regular Java reference
    */
    
    @ManyToMany(mappedBy = "demands") 
    private Set<TechnologieCiselnik> technologies = new HashSet<>();
    
    @Column(length=255, name="SPECIFIKACE")
    @Required
    @TextArea
    String specifikace;//JL:§.specifikace
    
    @ElementCollection
    @ListProperties("standardProduct.number, standardProduct.description, quantity")
    Collection<StandardDemandItem> standardDemandItems;
    
    @ElementCollection
    @ListProperties("description, unit, quantity")
    Collection<NonstandardDemandItem> nonstandardDemandItems;
    
    @Files // A complete image gallery is available
    @Column(length=32) // The 32 length string is for storing the key of the gallery
    String photos;
    @TextArea
    String remarks;
 
}