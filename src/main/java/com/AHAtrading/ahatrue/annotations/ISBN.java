package com.AHAtrading.ahatrue.annotations;

import java.lang.annotation.*;
import org.hibernate.validator.*;
//import  com.AHAtrading.ahatrue.validators.*;

//@ValidatorClass(ISBNValidator.class) //JL: odkomentovano pro funkcnost
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBN {  
	
	boolean search() default true;  	
	String message() default "ISBN does not exist";  
									
}