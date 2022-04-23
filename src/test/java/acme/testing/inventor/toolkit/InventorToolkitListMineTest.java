package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListMineTest extends TestHarness{
	
	// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/toolkit/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String code, final String title) {

			super.signIn("inventor1", "inventor1");

			super.clickOnMenu("Inventor", "List my Toolkits");
			super.checkListingExists();
			super.sortListing(0, "asc");

			super.checkColumnHasValue(recordIndex, 0, code);
			super.checkColumnHasValue(recordIndex, 1, title);
			


			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/toolkit/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTestAnonymous(final int recordIndex, final String code, final String title) {

			super.navigate("/inventor/toolkit/list-mine");
			super.checkPanicExists();
		
		}
		
		
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/toolkit/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(30)
		public void negativeTestAdministrator(final int recordIndex, final String code, final String title) {
			super.signIn("administrator", "administrator");
			super.navigate("/inventor/toolkit/list-mine");
			super.checkPanicExists();
			super.signOut();
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/toolkit/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(40)
		public void negativeTestPatron(final int recordIndex, final String code, final String title) {
			super.signIn("patron1", "patron1");
			super.navigate("/inventor/toolkit/list-mine");
			super.checkPanicExists();
			super.signOut();
		}
		
		
		

		
		
}
