package acme.helpers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRate;
import acme.entities.exchange.MoneyExchangeCache;
import acme.forms.MoneyExchange;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractService;

@Service
public class MoneyExchangeHelper implements AbstractService<Authenticated, MoneyExchange> {

	@Autowired
	protected MoneyExchangeRepository repository;
	
	public MoneyExchange computeMoneyExchange(final Money source, final String targetCurrency) {
		assert source != null;
		assert !StringHelper.isBlank(targetCurrency);

		final MoneyExchangeCache cache;
		String srcCurrency;
		
		srcCurrency = source.getCurrency();
		
		cache=this.repository.findMoneyExchangeInCache(srcCurrency, targetCurrency);
		
		if(cache!=null) {
		
			Calendar calendar;
			Date lastUpdate;
			Date lastValidDate;
			
			lastUpdate = cache.getLastUpdate();
			
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, -2);
			
			lastValidDate = calendar.getTime();
		
			MoneyExchange result;
			
			if(lastValidDate.after(lastUpdate)) {	//No valid db entry
				
				this.repository.delete(cache);
				
				result= this.getMoneyExchange(source, targetCurrency);
	
				return result;
				
			}else{									//Valid db entry
				
				Double targetAmount, rate;
				Money target;
				
				rate = cache.getRate();
				targetAmount = rate * source.getAmount();

				target = new Money();
				target.setAmount(targetAmount);
				target.setCurrency(targetCurrency);
				
				result = new MoneyExchange();
				result.setSource(source);
				result.setTargetCurrency(targetCurrency);
				result.setDate(cache.getLastUpdate());
				result.setTarget(target);
	
				return result;
			}
		}else {
			final MoneyExchange result;
			
			result= this.getMoneyExchange(source, targetCurrency);

			return result;
		}
		
		
	}
		
		
	public MoneyExchange getMoneyExchange(final Money source, final String targetCurrency) {

		MoneyExchange result;
		MoneyExchangeCache moneyCache;
		RestTemplate api;
		ExchangeRate record;
		String sourceCurrency;
		Double sourceAmount, targetAmount, rate;
		Money target;

		try {
			api = new RestTemplate();

			sourceCurrency = source.getCurrency();
			sourceAmount = source.getAmount();

			record = api.getForObject( //
				"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
				ExchangeRate.class, //
				sourceCurrency, //
				targetCurrency //
			);

			assert record != null;
			rate = record.getRates().get(targetCurrency);
			targetAmount = rate * sourceAmount;

			target = new Money();
			target.setAmount(targetAmount);
			target.setCurrency(targetCurrency);

			result = new MoneyExchange();
			result.setSource(source);
			result.setTargetCurrency(targetCurrency);
			result.setDate(record.getDate());
			result.setTarget(target);
			
			moneyCache = new MoneyExchangeCache();
			moneyCache.setSourceCurrency(source.getCurrency());
			moneyCache.setTargetCurrency(targetCurrency);
			moneyCache.setRate(record.getRates().get(targetCurrency));
			moneyCache.setLastUpdate(Calendar.getInstance().getTime());
			
			this.repository.save(moneyCache);

				
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
		
	}

}
