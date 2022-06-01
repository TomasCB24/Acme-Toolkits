package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListMineTest extends TestHarness {
	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String creationDate, final String initialPeriodDate, final String finalPeriodDate, final String link, final String company, final String statement, final String patronLink) {

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, creationDate);
		super.checkColumnHasValue(recordIndex, 2, legalStuff);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("initialPeriodDate", initialPeriodDate);
		super.checkInputBoxHasValue("finalPeriodDate", finalPeriodDate);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("patronLink", patronLink);

		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		// HINT: the framework doesn't provide enough support to implement this test case,
		// HINT+ so it must be performed manually:
		// HINT+ a) list the patronages with a role other than "Inventor";
		// HINT+ b) as a inventor, list the patronages of another inventor, other than the one logged in;
	}
	
}