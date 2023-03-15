package com.AHAtrading.ahatrue.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;
//https://www.javatpoint.com/hibernate-table-per-subclass-using-annotation-tutorial-example
@Entity
@Table(name="ZAMESTNANEC")
@Getter @Setter
@PrimaryKeyJoinColumn(name="ID")  
public class Employee extends Person {
	
	/*@Column(length=30,name="EMAIL")
	@Required
	String email;
	@Column(length=30,name="TEL")
	@Required
	String phoneNumber;*/
	@Column(length=10,name="NICK")
	@Required
	String nick;
	@Column(length=255,name="POZN")
	String remark;
	
	 @ManyToMany(mappedBy = "employees")
	    private Set<Activity> activities = new HashSet<>();
}
