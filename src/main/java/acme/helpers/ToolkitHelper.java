package acme.helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.features.any.item.AnyItemRepository;
import acme.features.any.toolkit.AnyToolkitRepository;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractService;


@Service
public class ToolkitHelper implements AbstractService<Any, Toolkit>  {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyToolkitRepository repository;
		
		@Autowired
		protected AuthenticatedMoneyExchangePerformService moneyExchangeService;
		
		@Autowired
		protected AnyItemRepository anyItemRepository;
		
		
		public Money getToolkitRetailPrice(final Toolkit entity) {
			final Money retailPrice = new Money();
			final Double amount;
			final String currency;

			
			final List<Money> retailPrices = this.repository.getRetailPricesByToolkitId(entity.getId()).stream().collect(Collectors.toList());
			
			final Set<String> currencies = new HashSet<String>();	// conjunto de monedas
			
			retailPrices.forEach(m -> currencies.add(m.getCurrency()));
			
			
			final SystemConfiguration sc = this.repository.findSystemConfiguration();
			currency = sc.getSystemCurrency();
			
			
			if(currencies.size()==1) {	// si tienen la misma moneda los sumo y multiplico (no tiene por qué ser la moneda de mi systemconfiguration
				amount = this.repository.computeRetailPriceByToolkitId(entity.getId());
				
				final Money m = new Money();
				m.setAmount(amount);
				m.setCurrency(currencies.iterator().next());
				
				final Money res;
				res = this.moneyExchangeService.computeMoneyExchange(m, currency).target;
				retailPrice.setAmount(res.getAmount());
				
				
				
				
			} else {  // si no, tengo que convertir CADA retailPrice de CADA item al currency del SystemConfiguration, y luego multiplicar por las cantidades y sumar
				
				final List<Item> itemsOnToolkit = this.repository.findItemsByToolkitId(entity.getId()).stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
				
				retailPrice.setAmount(0.0);
				
				
				for(final Item i : itemsOnToolkit) {
					final Double rPriceAmount = retailPrice.getAmount();
					
					final Integer quantity = this.anyItemRepository.findQuantityForItemInToolkit(entity.getId(), i.getId());
					final Money res;
					res = this.moneyExchangeService.computeMoneyExchange(i.getRetailPrice(), currency).getTarget();
					
					retailPrice.setAmount(rPriceAmount +  quantity*res.getAmount());
					
					
				
					
				}
				
				
				
			}
			retailPrice.setCurrency(currency);
			
			return retailPrice;
		}

}
