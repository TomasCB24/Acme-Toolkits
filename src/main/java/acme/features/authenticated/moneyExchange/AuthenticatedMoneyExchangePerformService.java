package acme.features.authenticated.moneyExchange;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRate;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractPerformService;

@Service
public class AuthenticatedMoneyExchangePerformService implements AbstractPerformService<Authenticated, MoneyExchange> {
	
	@Override
	public boolean authorise(final Request<MoneyExchange> request) {
		assert request != null;
		
		return true;
		
	}
	
	
	@Override
	public void bind(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "source", "targetCurrency", "date", "target");
	}
	
	@Override
	public void unbind(final Request<MoneyExchange> request, final MoneyExchange entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "source", "targetCurrency", "date", "target");
	}
	
	@Override
	public MoneyExchange instantiate(final Request<MoneyExchange> request) {
		assert request != null;

		MoneyExchange result;

		result = new MoneyExchange();

		return result;
	}
	
	
	@Override
	public void validate(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}
	
	@Override
	public void perform(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		Money source, target;
		String targetCurrency;
		Date date;
		MoneyExchange exchange;

		source = request.getModel().getAttribute("source", Money.class);
		targetCurrency = request.getModel().getAttribute("targetCurrency", String.class);
		exchange = this.computeMoneyExchange(source, targetCurrency);
		errors.state(request, exchange != null, "*", "authenticated.money-exchange.form.label.api-error");
		if (exchange == null) {
			entity.setTarget(null);
			entity.setDate(null);
		} else {
			target = exchange.getTarget();
			entity.setTarget(target);
			date = exchange.getDate();
			entity.setDate(date);
		}
	}
	
	
	// Ancillary methods ------------------------------------------------------

	public MoneyExchange computeMoneyExchange(final Money source, final String targetCurrency) {
		assert source != null;
		assert !StringHelper.isBlank(targetCurrency);

		MoneyExchange result;
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
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}


}
