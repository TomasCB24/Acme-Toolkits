package acme.testing.administrator.systemconfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationShowCurrencyTest extends TestHarness {
	
	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemconfiguration/show-currency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAuthenticated(final String recordIndex, final String acceptedCurrencies, final String systemCurrency, final String strongSpamWords, final String strongSpamThreshold, final String weakSpamWords, final String weakSpamThreshold) {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "System configuration");
		super.checkFormExists();
		
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("strongSpamWords", strongSpamWords);
		super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
		super.checkInputBoxHasValue("weakSpamWords", weakSpamWords);
		super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);
		
		
		
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemconfiguration/show-currency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void hackingTestAnonymous(final String recordIndex, final String acceptedCurrencies, final String systemCurrency) {
		
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemconfiguration/show-currency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void hackingTestAuthenticated(final String recordIndex, final String acceptedCurrencies, final String systemCurrency) {
		super.signIn("patron1", "patron1");
		
		super.navigate("/administrator/system-configuration/show");
		super.checkPanicExists();
		
	}
		
		

}
