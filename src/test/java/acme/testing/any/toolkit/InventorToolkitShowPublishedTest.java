package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitShowPublishedTest extends TestHarness{

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/show-published.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestShowToolkits(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String retailPrice) {

		super.clickOnMenu("Anonymous", "List Published Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		
		
		if(!link.equals("null")) {
			super.checkInputBoxHasValue("link", link);
		} else {
			super.checkInputBoxHasValue("link", "");
		}
		
		super.checkInputBoxHasValue("retailPrice", retailPrice);

		
		
		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
	
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list-items-second-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTestListingItemsOfToolkits(final int recordIndex, final String type, final String name, final String retailPrice, final String quantity) {
		
		super.clickOnMenu("Anonymous", "List Published Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);	// second toolkit
		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, type);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		super.checkColumnHasValue(recordIndex, 3, quantity);
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/show-items-second-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTestShowingItemsOfToolkits(final int recordIndex, final String type, final String name, final String retailPrice, final String code, final String technology, final String description, final String link) {
		
		super.clickOnMenu("Anonymous", "List Published Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);	// second toolkit
		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();

		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		
		if(!link.equals("null")) {
			super.checkInputBoxHasValue("link", link);
		} else {
			super.checkInputBoxHasValue("link", "");
		}
		
		
	}
	

}
