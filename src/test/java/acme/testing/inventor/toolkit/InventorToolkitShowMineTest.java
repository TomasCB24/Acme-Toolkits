package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitShowMineTest extends TestHarness{

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/show-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestShowToolkits(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String retailPrice) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		
		
		if(!link.equals("null")) {
			super.checkInputBoxHasValue("link", link);
		} else {
			super.checkInputBoxHasValue("link", "");
		}
		
		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		
		super.checkListingExists();
		super.signOut();
	
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-items-first-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTestListingItemsOfToolkits(final int recordIndex, final String type, final String name, final String code, final String quantity) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(0);	// first toolkit
		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		
		super.checkListingExists();
		super.sortListing(1, "asc");

		super.checkColumnHasValue(recordIndex, 0,quantity);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, name);
		super.checkColumnHasValue(recordIndex, 3, type);
		
		super.signOut();
		
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/show-items-second-toolkit.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTestShowingItemsOfToolkits(final int recordIndex, final String type, final String name, final String retailPrice, final String code, final String technology, final String description, final String link) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(1);	// second toolkit
		super.checkButtonExists("Items");
		super.clickOnButton("Items");
		
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("type", type);
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
		
		super.signOut();
	}
	
	@Test
	@Order(40)
	public void hackingTest() {
		// HINT: the framework doesn't provide enough support to implement this test case,
		// HINT+ so it must be performed manually:
		// HINT+ a) show a toolkit with a role other than "Inventor";
		// HINT+ b) as a inventor, show a toolkit of another inventor, other than the one logged in;
	}

}
