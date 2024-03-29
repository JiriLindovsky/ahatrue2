package com.AHAtrading.ahatrue.actions;

import org.openxava.actions.*;  

public class SearchInvoiceFromOrderAction 
	extends ReferenceSearchAction {  

	public void execute() throws Exception {
		super.execute();  
		int customerNumber = 
			getPreviousView()  
				.getValueInt("customer.number");
													
		if (customerNumber > 0) {  	
			getTab().setBaseCondition("${customer.number} = " + customerNumber);
		}
	}
}