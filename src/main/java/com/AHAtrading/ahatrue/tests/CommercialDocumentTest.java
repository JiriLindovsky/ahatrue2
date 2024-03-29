package com.AHAtrading.ahatrue.tests;

import java.text.*;
import java.util.*;
import javax.persistence.*;
import org.openxava.tests.*;
import org.openxava.util.*;
import static org.openxava.jpa.XPersistence.*;  

abstract public class CommercialDocumentTest extends ModuleTestBase {
	
	private String number;  
	private String model;  

	public CommercialDocumentTest(String testName, String moduleName) {
		super(testName, "Invoicing", moduleName);				
		this.model = moduleName;  
	}
		
	public void testCreate() throws Exception {
		calculateNumber();  
		verifyDefaultValues();
		chooseCustomer();		
		addDetails();		
		setOtherProperties();		
		save(); 
		verifyAmountAndEstimatedProfit();  
		verifyCreated();				
		remove(); 
	}
	private void verifyDefaultValues() throws Exception {
		execute("CRUD.new");
		assertValue("year", getCurrentYear());				
		assertValue("number", "");  
		assertValue("date", getCurrentDate());
		assertValue("vatPercentage", "18");
	}								
	
	private void chooseCustomer() throws Exception {
		setValue("customer.number", "1");
		assertValue("customer.name", "FRANCISCO JAVIER PANIZA LUCAS");
	}

	private void addDetails() throws Exception {
		assertCollectionRowCount("details", 0);
		execute("Collection.new", 
			"viewObject=xava_view_section0_details"); 
		setValue("product.number", "1");
		assertValue("product.description", 
			"Peopleware: Productive Projects and Teams");
		assertValue("pricePerUnit", 
			"31.00");
		setValue("quantity", "2");
		assertValue("amount", "62.00");   
		execute("Collection.save"); 
		assertNoErrors();
		assertCollectionRowCount("details", 1);			
		assertValue("number", getNumber());  

		assertValue("baseAmount", "62.00");	  	
		assertValue("vat", "11.16");  			
		assertValue("totalAmount", "73.16"); 	
			
		execute("Collection.new", 
			"viewObject=xava_view_section0_details"); 
		setValue("product.number", "2");
		assertValue("product.description", 
			"Arco iris de lágrimas");
		assertValue("pricePerUnit", 
			"15.00"); 
		setValue("pricePerUnit", "10.00");  
		setValue("quantity", "1");
		assertValue("amount", "10.00");   
		execute("Collection.save"); 
		assertNoErrors();
		assertCollectionRowCount("details", 2);
					
		assertValue("baseAmount", "72.00");		
		assertValue("vat", "12.96");				
		assertValue("totalAmount", "84.96");	
	}	
	
	private void verifyAmountAndEstimatedProfit() throws Exception { 
		execute("Mode.list");  
		setConditionValues(new String [] {	 
			getCurrentYear(), getNumber() 	
		});
		execute("List.filter");  
		assertValueInList(0, 0, getCurrentYear());  
		assertValueInList(0, 1, getNumber());  
		assertValueInList(0, "amount", "84.96");  
		assertValueInList(0, "estimatedProfit", "8.50");  
		execute("Mode.detailAndFirst");  
	}
	
	private void setOtherProperties() throws Exception {
		setValue("remarks", "This is a JUNIT test");
	}

	private void save() throws Exception {
		execute("CRUD.save");
		assertNoErrors();
			
		assertValue("customer.number", "");
		assertCollectionRowCount("details", 0);
		assertValue("remarks", "");
	}

	private void verifyCreated() throws Exception {			
		// In the rest of the test we assert that the values are the correct ones
		assertValue("year", getCurrentYear()); 
		assertValue("number", getNumber());
		assertValue("date", getCurrentDate());
			
		assertValue("customer.number", "1");
		assertValue("customer.name", "FRANCISCO JAVIER PANIZA LUCAS");
			
		assertCollectionRowCount("details", 2);
			
		// Row 0
		assertValueInCollection("details", 0, "product.number", "1");
		assertValueInCollection("details", 0, "product.description", 
			"Peopleware: Productive Projects and Teams");
		assertValueInCollection("details", 0, "quantity", "2");
			
		// Row 1
		assertValueInCollection("details", 1, "product.number", "2");
		assertValueInCollection("details", 1, "product.description", 
			"Arco iris de lágrimas");
		assertValueInCollection("details", 1, "quantity", "1");		
			
		assertValue("remarks", "This is a JUNIT test");
	}
	
	public void testTrash() throws Exception { 
		assertListOnlyOnePage();  

		int initialRowCount = getListRowCount();		
		String year1 = getValueInList(0, 0);
		String number1 = getValueInList(0, 1);
		execute("Mode.detailAndFirst");
		execute("Invoicing.delete");  
		execute("Mode.list");
			
		assertListRowCount(initialRowCount - 1);  
		assertDocumentNotInList(year1, number1);  
									
		String year2 = getValueInList(0, 0);
		String number2 = getValueInList(0, 1);
		checkRow(0);  
		execute("Invoicing.deleteSelected");  
		assertListRowCount(initialRowCount - 2);   
		assertDocumentNotInList(year2, number2);   
													
		changeModule(model + "Trash");  
		assertListOnlyOnePage();
		int initialTrashRowCount = getListRowCount(); 
			
		assertDocumentInList(year1, number1);  
		assertDocumentInList(year2, number2);  

		int row1 = getDocumentRowInList(year1, number1);
		execute("Trash.restore", "row=" + row1);
		assertListRowCount(initialTrashRowCount - 1); 
		assertDocumentNotInList(year1, number1);  
													
		int row2 = getDocumentRowInList(year2, number2);
		checkRow(row2);
		execute("Trash.restore");
		assertListRowCount(initialTrashRowCount - 2);  
		assertDocumentNotInList(year2, number2);  
																
		changeModule(model);
		assertListRowCount(initialRowCount);	
		assertDocumentInList(year1, number1);	
		assertDocumentInList(year2, number2);		
	}
	
	private void assertListOnlyOnePage() throws Exception {
		assertListNotEmpty();  
		assertTrue("Must be less than 10 rows to run this test", 
				getListRowCount() < 10);		
	}
	
	private void assertDocumentNotInList(String year, String number) 
		throws Exception 
	{
		assertTrue(
			"Document " + year + "/" + number +" must not be in list",
			getDocumentRowInList(year, number) < 0);
	}
		
	protected void assertDocumentInList(String year, String number) 
		throws Exception 
	{
		assertTrue(
			"Document " + year + "/" + number + " must be in list",
			getDocumentRowInList(year, number) >= 0);
	}
		
	protected int getDocumentRowInList(String year, String number) 
		throws Exception 
	{
		int c = getListRowCount();
		for (int i=0; i<c; i++) {
			if (	year.equals(getValueInList(i, 0)) &&
				number.equals(getValueInList(i, 1))) 
			{
				return i;				
			}
		}		
		return -1;
	}

	private void remove() throws Exception {
		execute("Invoicing.delete");
		assertNoErrors();
	}
		
	private String getCurrentYear() {  
		return new SimpleDateFormat("yyyy").format(new Date());  																	
	}
		
	private String getCurrentDate() {  
		return DateFormat.getDateInstance(  	
			DateFormat.SHORT).format(new Date());
	}
			
	private void calculateNumber() {
		Query query = getManager().
			createQuery(
				"select max(i.number) from " + 
				model +  
				" i where i.year = :year"); 
		query.setParameter("year", Dates.getYear(new Date()));
		Integer lastNumber = (Integer) query.getSingleResult();
		if (lastNumber == null) lastNumber = 0;
		number = Integer.toString(lastNumber + 1);
	}
	
	private String getNumber() {
		return number;
	}

	protected void assertCustomerInList(String customerNumber) throws Exception {
		assertValueForAllRows(3, customerNumber);  
	}	
	
	protected void assertValueForAllRows(int column, String value) 
		throws Exception 
	{ 
		assertListNotEmpty();
		int c = getListRowCount();
		for (int i=0; i<c; i++) {
			if (!value.equals(
				getValueInList(i, column)))  
			{  
				fail("Column " + column + " in row " + i + " is not " + value); 
			}
		}		
	}	
	
}