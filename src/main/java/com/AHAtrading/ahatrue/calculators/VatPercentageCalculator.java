package com.AHAtrading.ahatrue.calculators;

import org.openxava.calculators.*;
import com.AHAtrading.ahatrue.util.*;

public class VatPercentageCalculator implements ICalculator {

	public Object calculate() throws Exception {		
		return InvoicingPreferences.getDefaultVatPercentage();
	}

}
