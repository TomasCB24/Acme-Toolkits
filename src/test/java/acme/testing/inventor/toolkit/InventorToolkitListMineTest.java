package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListMineTest extends TestHarness{
	
	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, final String retailPrice) {

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List my Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);
		


		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {

		super.navigate("/inventor/toolkit/list-mine");
		super.checkPanicExists();
		
		super.signIn("administrator", "administrator");
		super.navigate("/inventor/toolkit/list-mine");
		super.checkPanicExists();
		super.signOut();
	
		super.signIn("patron1", "patron1");
		super.navigate("/inventor/toolkit/list-mine");
		super.checkPanicExists();
		super.signOut();
		
		// HINT: the framework doesn't provide enough support to implement this test case,
		// HINT+ so it must be performed manually:
		// HINT+ a) as an inventor, list the toolkits of another inventor, other than the one logged in;
		
	}
		
}
