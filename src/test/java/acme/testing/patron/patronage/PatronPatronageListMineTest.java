package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageListMineTest  extends TestHarness {
	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, final String legalStuff, final String budget, final String creationDate, final String initialPeriodDate, final String finalPeriodDate, final String link, final String company, final String statement, final String inventorLink) {
		
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "My patronages");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, creationDate);
		super.checkColumnHasValue(recordIndex, 2, legalStuff);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		//super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationDate", creationDate);
		super.checkInputBoxHasValue("initialPeriodDate", initialPeriodDate);
		super.checkInputBoxHasValue("finalPeriodDate", finalPeriodDate);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("inventorLink", inventorLink);
		
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
	public void negativeTestPatron() {
		super.signIn("inventor1", "inventor1");
		super.navigate("/patron/patronage-report/list-mine");

		super.checkPanicExists();
		super.signOut();
	}
}
