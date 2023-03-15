package com.AHAtrading.ahatrue.model;
 
import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
 
@Embeddable // We use @Embeddable instead of @Entity
@Getter @Setter
@Table(name="ADRESA")
public class Address {
 
    @Column(length = 30, name="ULICE") 
    String street;//JL:ulice (bez cisla)
 
    @Column(length = 15, name="CPCORD")
    String cpcord;//JL:cislo popisné/číslo ordinální
    
    @Column(length = 5,name="PSC")
    int zipCode;//JL:PSČ
 
    @Column(length = 20,name="OBEC")//to-do: redundantní vůči psč -upravit model
    String city;
    
    @Column(length = 20,name="OBECCAST")
    String cityPart;//to-do:redundantní vůči psč - upravit mdel
 
    @Column(length = 30,name="STAT")
    @DefaultValueCalculator(DefaultCountryCalculator.class)
    String state;//JL: stát
    
   /* @Column(length = 30,name="USER")
    @DefaultValueCalculator(DefaultUserCalculator.class)
    String user;*/
}