package acme.testing.inventor.patronagereport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness {
	// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String serialNumber, final String patronageCode,final String memorandum,final String link) {
			super.signIn("inventor1", "inventor1");
			super.clickOnMenu("Inventor", "Patronage Report");
			super.checkListingExists();
			super.clickOnButton("Create");
			
			super.checkFormExists();
			
			super.fillInputBoxIn("memorandum", memorandum);
			super.fillInputBoxIn("serialNumber", serialNumber);		
			super.fillInputBoxIn("link", link);
			super.fillInputBoxIn("patronage-code", patronageCode);
			super.fillInputBoxIn("confirmation", "true");
			super.clickOnSubmit("Create");
			
			super.checkListingExists();
			super.sortListing(1, "desc");
			super.checkColumnHasValue(recordIndex, 1, memorandum);
			super.clickOnListingRecord(recordIndex);

			super.checkFormExists();
			super.checkInputBoxHasValue("memorandum", memorandum);
			super.checkInputBoxHasValue("link", link);
			

			super.signOut();
			
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTest(final int recordIndex, final String serialNumber, final String patronageCode,final String memorandum,final String link) {

			super.signIn("inventor1", "inventor1");
			super.clickOnMenu("Inventor", "Patronage Report");
			super.checkListingExists();
			super.clickOnButton("Create");
			
			super.checkFormExists();
			
			super.fillInputBoxIn("memorandum", memorandum);
			super.fillInputBoxIn("serialNumber", serialNumber);		
			super.fillInputBoxIn("link", link);
			super.fillInputBoxIn("patronage-code", patronageCode);
			super.fillInputBoxIn("confirmation", "true");
			super.clickOnSubmit("Create");
			super.checkErrorsExist();
			super.signOut();
		}
		
		@Test
		@Order(30)
		public void hackingTest() {
			super.checkNotLinkExists("Account");
			super.navigate("/inventor/patronage-report/create");
			super.checkPanicExists();

			super.signIn("patron1", "patron1");
			super.navigate("/inventor/patronage-report/create");
			super.checkPanicExists();
			super.signOut();
			
			super.signIn("administrator", "administrator");
			super.navigate("/inventor/patronage-report/create");
			super.checkPanicExists();
			super.signOut();
		}

}
