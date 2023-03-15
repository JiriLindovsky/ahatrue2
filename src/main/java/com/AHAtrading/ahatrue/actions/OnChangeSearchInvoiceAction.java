package com.AHAtrading.ahatrue.actions;

import java.util.*;

import com.AHAtrading.ahatrue.model.Invoice;
import org.openxava.actions.*;
import com.AHAtrading.ahatrue.model.*;
import org.openxava.model.*;
import org.openxava.view.*;

public class OnChangeSearchInvoiceAction 
	extends OnChangeSearchAction {  	
												
	public void execute() throws Exception {
		super.execute();  
		Map keyValues = getView()
			.getKeyValuesWithValue();  
		if (keyValues.isEmpty()) return; 
		Invoice invoice = (Invoice)
			MapFacade.findEntity(getView().getModelName(), keyValues);
		View customerView = getView().getRoot().getSubview("customer"); 
		int customerNumber = customerView.getValueInt("number");		
		if (customerNumber == 0) {  
			customerView.setValue("number", invoice.getCustomer().getId());//JL: change getNumber()->getId()
			customerView.refresh();
		}
		else {  
			if (customerNumber != invoice.getCustomer().getId())//JL: change getNumber()->getId()
			 {
				addError("invoice_customer_not_match", 
					invoice.getCustomer().getId(), invoice, customerNumber); //JL: change getNumber()->getId()
				getView().clear();
			}
		}
	}
	
}
