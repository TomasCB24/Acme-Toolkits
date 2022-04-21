package acme.testing.patron.patronagereport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportListMineTest extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage-report/list-patronage-reports.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String sequenceNumber, final String memorandum) {
		super.signIn("patron1", "patron1");
		super.clickOnMenu("Patron", "Patronage Report");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, sequenceNumber);
		super.checkColumnHasValue(recordIndex, 1, memorandum);
		
		super.signOut();
		
	}
	
	@Test
	@Order(20)
	public void negativeTestAnonymous() {

		super.navigate("/patron/patronage-report/list-mine");

		super.checkPanicExists();
	}
	
	@Test
	@Order(30)
	public void negativeTestAdministrator() {
		super.signIn("administrator", "administrator");
		super.navigate("/patron/patronage-report/list-mine");

		super.checkPanicExists();
		super.signOut();
	}
	
	@Test
	@Order(40)
	public void negativeTestInventor() {
		super.signIn("inventor1", "inventor1");
		super.navigate("/patron/patronage-report/list-mine");

		super.checkPanicExists();
		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------
	
}
