package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AuthenticatedAnnouncementListTest extends TemporalAwareTestHarness  {

	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list-recent.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int deltaDays, final String title) {
		
		String moment;

        moment = super.computeDeltaMoment(deltaDays);
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Authenticated", "List recent announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.signOut();
		
	}
	
	
	@Test
	@Order(20)
	public void negativeTestAnonymous() {
		super.navigate("/inventor/announcement/list-recent");

		super.checkPanicExists();
	}
	
}
