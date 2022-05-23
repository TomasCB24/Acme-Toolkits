package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemListMineTest extends TestHarness{
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestListingItems(final int recordIndex, final String type, final String name, final String retailPrice, final String code, final String technology, final String description, final String link, final String inventor) {
		
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List my Items");
		super.checkListingExists();
		super.sortListing(1, "asc");

		super.checkColumnHasValue(recordIndex, 0, type);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, retailPrice);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("inventor", inventor);
		
		if(!link.equals("null")) {
			super.checkInputBoxHasValue("link", link);
		} else {
			super.checkInputBoxHasValue("link", "");
		}
		
	}

}
