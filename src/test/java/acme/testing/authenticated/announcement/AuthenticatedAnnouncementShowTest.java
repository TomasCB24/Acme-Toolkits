package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TemporalAwareTestHarness;

public class AuthenticatedAnnouncementShowTest extends TemporalAwareTestHarness{
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final int deltaDays, final String title, final String body, final String link) {
		
		String moment;

        moment = super.computeDeltaMoment(deltaDays);
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Authenticated", "List recent announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("creationMoment", moment);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("body", body);
		
		if(!link.equals("null")) {
			super.checkInputBoxHasValue("link", link);
		} else {
			super.checkInputBoxHasValue("link", "");
		}


		super.signOut();
		
	}

	// Ancillary methods ------------------------------------------------------

}
