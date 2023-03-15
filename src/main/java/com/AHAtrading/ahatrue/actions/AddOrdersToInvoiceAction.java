package com.AHAtrading.ahatrue.actions;

import org.openxava.actions.AddElementsToCollectionAction;
import com.AHAtrading.ahatrue.model.*;
import org.openxava.model.MapFacade;
import org.openxava.util.XavaException;
import org.openxava.validators.ValidationException;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import java.rmi.RemoteException;
import java.util.Map;

public class AddOrdersToInvoiceAction 
	extends AddElementsToCollectionAction {
											
	public void execute() throws Exception { 	
		super.execute();
		getView().refresh();  
	}						
																
	protected void associateEntity(Map keyValues)   
		throws ValidationException,  
			XavaException, ObjectNotFoundException,
			FinderException, RemoteException 
	{
		super.associateEntity(keyValues);
		Order order = (Order) MapFacade.findEntity("Order", keyValues);
		order.copyDetailsToInvoice();
	}

}