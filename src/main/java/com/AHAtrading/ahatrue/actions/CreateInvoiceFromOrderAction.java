package com.AHAtrading.ahatrue.actions;

import org.openxava.actions.*;
import com.AHAtrading.ahatrue.model.*;
import org.openxava.jpa.*; 	
import org.openxava.model.*;
																
public class CreateInvoiceFromOrderAction 
	extends ViewBaseAction
	implements IHideActionAction  
{  

	
	private boolean hideAction = false;  
	
	public void execute() throws Exception {
		Object oid = getView().getValue("oid");
		if (oid == null) {  
			addError(
				"impossible_create_invoice_order_not_exist");
			return;
		}
		MapFacade.setValues("Order",  
			getView().getKeyValues(), getView().getValues());
		
		Order order = XPersistence.getManager().find(  
				Order.class, 							
				getView().getValue("oid"));
		order.createInvoice();  
		getView().refresh();  
		addMessage("invoice_created_from_order",  
			order.getInvoice());
		hideAction = true;  		
	}

	public String getActionToHide() {  
		return hideAction?"Order.createInvoice":null;		
	}

}
