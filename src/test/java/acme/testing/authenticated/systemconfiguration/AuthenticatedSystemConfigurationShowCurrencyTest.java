package acme.testing.authenticated.systemconfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedSystemConfigurationShowCurrencyTest extends TestHarness {
	
	
	// Test cases -------------------------------------------------------------
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/systemconfiguration/show-currency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAuthenticated(final String recordIndex, final String acceptedCurrencies, final String systemCurrency) {
		super.signIn("patron1", "patron1");
		super.clickOnMenu("Authenticated", "Currency System Configuration");
		super.checkFormExists();
		
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("systemCurrency", systemCurrency);
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/systemconfiguration/show-currency.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void hackingTestAnonymous(final String recordIndex, final String acceptedCurrencies, final String systemCurrency) {
		
		super.navigate("/authenticated/system-configuration/show");
		super.checkPanicExists();
		
	}
	

	
	
	
	
}
