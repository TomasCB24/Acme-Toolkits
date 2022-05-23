package acme.testing.anonymous.useraccount;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class AnonymousBecomeInventor extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void positiveTest() {
		super.signUp("user1", "user1", "user1", "surname1", "user1@gmail.com");
		super.signIn("user1", "user1");
		
		super.clickOnMenu("Account", "Become an inventor");
		super.fillInputBoxIn("company", "Company1");
		super.fillInputBoxIn("statement", "Statement1");
		super.clickOnSubmit("Register");
		
		super.navigate("/inventor/patronage-report/list-mine");
		super.checkListingExists();

		super.signOut();
	}

	@Test
	@Order(20)
	public void negativeTest() {
		super.signUp("user2", "user2", "user2", "surname2", "user2@gmail.com");
		super.signIn("user2", "user2");
		
		super.navigate("/patron/patronage-report/create");
		super.checkPanicExists();

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
