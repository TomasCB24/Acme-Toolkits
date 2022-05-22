package acme.testing.administrator.systemconfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness {
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemconfiguration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final String recordIndex, final String acceptedCurrencies, final String systemCurrency, final String strongSpamWords, final String strongSpamThreshold, final String weakSpamWords, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System configuration");

		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamWords", strongSpamWords);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamWords", weakSpamWords);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Administrator", "System configuration");
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamWords", strongSpamWords);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamWords", weakSpamWords);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemconfiguration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negative(final String recordIndex, final String acceptedCurrencies, final String systemCurrency, final String strongSpamWords, final String strongSpamThreshold, final String weakSpamWords, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System configuration");

		super.fillInputBoxIn("systemCurrency", systemCurrency);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("strongSpamWords", strongSpamWords);
		super.fillInputBoxIn("strongSpamThreshold", strongSpamThreshold);
		super.fillInputBoxIn("weakSpamWords", weakSpamWords);
		super.fillInputBoxIn("weakSpamThreshold", weakSpamThreshold);
		super.clickOnSubmit("Update");
		super.checkErrorsExist();
		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Administrator");
		super.navigate("/administrator/system-configuration/update");
		super.checkPanicExists();

		super.signIn("patron1", "patron1");
		super.navigate("/administrator/system-configuration/update");
		super.checkPanicExists();
		super.signOut();
		
		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/system-configuration/update");
		super.checkPanicExists();
		
		super.signOut();
	}
	
}
