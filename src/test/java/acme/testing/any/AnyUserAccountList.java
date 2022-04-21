package acme.testing.any;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyUserAccountList extends TestHarness  {
	
	// Test cases -------------------------------------------------------------
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/list-user-account.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestAnonymous(final int recordIndex, final String username, final String name, final String surname, final String email) {
			super.clickOnMenu("Anonymous", "User Accounts List");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
		
			super.checkColumnHasValue(recordIndex, 0, username);
			super.checkColumnHasValue(recordIndex, 1, name);
			super.checkColumnHasValue(recordIndex, 2, surname);
			super.checkColumnHasValue(recordIndex, 3, email);			
			
		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/any/user-account/list-user-account.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTestAuthenticated(final int recordIndex, final String username, final String name, final String surname, final String email) {
			super.signIn("patron1", "patron1");
			super.clickOnMenu("Authenticated", "User Accounts List");
			super.checkListingExists();
			super.sortListing(0, "asc");
			
		
			super.checkColumnHasValue(recordIndex, 0, username);
			super.checkColumnHasValue(recordIndex, 1, name);
			super.checkColumnHasValue(recordIndex, 2, surname);
			super.checkColumnHasValue(recordIndex, 3, email);			
			
		}

}
