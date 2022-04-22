package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementListTest extends TestHarness  {

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String creationMoment, final String title, final String body, final String flag, final String link) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Authenticated", "List recent announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, body);
		super.checkColumnHasValue(recordIndex, 3, flag);
		super.checkColumnHasValue(recordIndex, 4, link);
		super.signOut();
		
	}
	
	@Test
	@Order(20)
	public void negativeTestAnonymous(final int recordIndex, final String creationMoment, final String title, final String body, final String flag, final String link) {
		super.navigate("/inventor/announcement/list-recent");

		super.checkPanicExists();
	}
	
}
