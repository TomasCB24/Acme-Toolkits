package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyItemListPublishedTest extends TestHarness{
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list-items.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestListingItemsOfToolkits(final int recordIndex, final String type, final String name, final String retailPrice, final String quantity) {
		
		super.clickOnMenu("Anonymous", "Published Items list");
		super.checkListingExists();
		super.sortListing(1, "asc");

		super.checkColumnHasValue(recordIndex, 0, type);
		super.checkColumnHasValue(recordIndex, 2, name);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, quantity);
		
	}
	
}
