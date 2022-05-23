package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class CreateTest extends TestHarness {

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String body,final String flag,final String link) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create new Announcement");
		super.checkFormExists();

		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);		
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("flag", flag);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");

		super.clickOnMenu("Authenticated", "List recent announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 1, title);
		super.clickOnListingRecord(recordIndex);

		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("link", link);
		

		super.signOut();
	}

	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/announcement/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String body,final String flag,final String link) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Create new Announcement");
		super.checkFormExists();

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("body", body);		
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("flag", flag);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();

		super.signIn("patron1", "patron1");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		super.signOut();
	}


	// Ancillary methods ------------------------------------------------------

}
 


