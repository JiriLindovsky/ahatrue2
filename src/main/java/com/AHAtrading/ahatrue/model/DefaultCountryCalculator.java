package com.AHAtrading.ahatrue.model;

import org.openxava.calculators.*;

public class DefaultCountryCalculator implements ICalculator {
	 
    @Override
    public Object calculate() throws Exception {
        return new String("ČR");
    }
}
