package acme.testing.any.useraccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountShowTest extends TestHarness  {
	
	// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/list-user-account.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestAnonymous(final int recordIndex, final String username, final String name, final String surname, final String email,final String roles) {
			super.clickOnMenu("Anonymous", "User Accounts List");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("username", username);
			super.checkInputBoxHasValue("name", name);
			super.checkInputBoxHasValue("surname", surname);
			super.checkInputBoxHasValue("email", email);
			super.checkInputBoxHasValue("roles", roles);
			
			
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/list-user-account.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestAuthenticated(final int recordIndex, final String username, final String name, final String surname, final String email,final String roles) {
			super.signIn("patron1", "patron1");
			super.clickOnMenu("Authenticated", "User Accounts List");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
			super.clickOnListingRecord(recordIndex);
			super.checkFormExists();
			super.checkInputBoxHasValue("username", username);
			super.checkInputBoxHasValue("name", name);
			super.checkInputBoxHasValue("surname", surname);
			super.checkInputBoxHasValue("email", email);
			super.checkInputBoxHasValue("roles", roles);
			
			
		}

}

