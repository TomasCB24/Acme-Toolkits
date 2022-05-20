package acme.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.patronages.Patronage;
import acme.features.authenticated.systemconfiguration.AuthenticatedSystemConfigurationRepository;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractService;


@Service
public class PatronageHelper implements AbstractService<Any, Patronage> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected MoneyExchangeHelper moneyExchangeService;
	
	@Autowired
	protected AuthenticatedSystemConfigurationRepository authSystemConfigRepo;
	
	public Money getPatronageBudget(final Patronage patronage) {
		final Money retailPrice = new Money();
		final Double amount;
		final String currency;
		final String patronageCurrency;

		final SystemConfiguration sc = this.authSystemConfigRepo.findSystemConfiguration();
		currency = sc.getSystemCurrency();
		
		patronageCurrency = patronage.getBudget().getCurrency();
		amount = patronage.getBudget().getAmount();
		if(!patronageCurrency.equals(currency)) {
			
			
			final Money m = new Money();
			m.setAmount(amount);
			m.setCurrency(patronageCurrency);
			
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
