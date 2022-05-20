package acme.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.items.Item;
import acme.features.any.item.AnyItemRepository;
import acme.features.authenticated.systemconfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractService;


@Service
public class ItemHelper implements AbstractService<Any, Item> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemRepository repository;
	
	@Autowired
	protected MoneyExchangeHelper moneyExchangeService;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository authSystemConfigRepo;

	
	
	public Money getItemRetailPrice(final Item item) {
		final Money retailPrice = new Money();
		final Double amount;
		final String currency;
		final String itemCurrency;

		

		final SystemConfiguration sc = this.authSystemConfigRepo.findSystemConfiguration();
		currency = sc.getSystemCurrency();
		
		itemCurrency = item.getRetailPrice().getCurrency();
		amount = item.getRetailPrice().getAmount();
		if(!itemCurrency.equals(currency)) {
			
			
			final Money m = new Money();
			m.setAmount(amount);
			m.setCurrency(itemCurrency);
			
			final Money res;
			res = this.moneyExchangeService.computeMoneyExchange(m, currency).target;
			retailPrice.setAmount(res.getAmount());
			
			
			
			
		} else {
			retailPrice.setAmount(amount);
		}
		retailPrice.setCurrency(currency);
		
		return retailPrice;
	}

}
