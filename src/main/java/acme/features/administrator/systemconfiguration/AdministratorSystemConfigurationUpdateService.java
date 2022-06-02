package acme.features.administrator.systemconfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;

	//All currencies accepted by the API used | Updated at 2022/06/02
	
	List<String> currenciesAPI = Arrays.asList("AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", 
		"AWG", "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL", "BSD", 
		"BTC", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLF", "CLP", "CNH", "CNY", "COP", 
		"CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP", "ERN", "ETB", "EUR", 
		"FJD", "FKP", "GBP", "GEL", "GGP", "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", 
		"HRK", "HTG", "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD", 
		"JPY", "KES", "KGS", "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", 
		"LRD", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", 
		"MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", 
		"PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", 
		"SDG", "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "SSP", "STD", "STN", "SVC", "SYP", "SZL", 
		"THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", 
		"UZS", "VES", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XCD", "XDR", "XOF", "XPD", "XPF", 
		"XPT", "YER", "ZAR", "ZMW", "ZWL");
	
	// AbstractUpdateService<Administrator, SystemConfiguration> ---------------------------

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		
		SystemConfiguration result;
		
		result = this.repository.findSystemConfiguration();
		
		return result;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "systemCurrency", "acceptedCurrencies",
			"strongSpamWords","strongSpamThreshold","weakSpamWords","weakSpamThreshold");
		
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("systemCurrency")) {
			
			final List<String> acceptedCurrenciesList = Arrays.asList(entity.getAcceptedCurrencies().split(","));
			
			errors.state(request, acceptedCurrenciesList.contains(entity.getSystemCurrency()), "systemCurrency", "administrator.system-configuration.form.error.system-currency");
		}
		
		if(!errors.hasErrors("acceptedCurrencies")) {
			
			final List<String> acceptedCurrenciesList = Arrays.asList(entity.getAcceptedCurrencies().split(","));
			
			Set<String> differentAcceptedCurrencies;
			
			differentAcceptedCurrencies= new HashSet<>(acceptedCurrenciesList);
			
			errors.state(request, acceptedCurrenciesList.size() == differentAcceptedCurrencies.size(), "acceptedCurrencies", "administrator.system-configuration.form.error.repeated-currency");
			errors.state(request, this.currenciesAPI.containsAll(differentAcceptedCurrencies), "acceptedCurrencies", "administrator.system-configuration.form.error.not-accepted-currency");
		
		}
		
		if(!errors.hasErrors("strongSpamWords")) {
			
			final List<String> strongSpamWordsList = Arrays.asList(entity.getStrongSpamWords().split(","))
															.stream()
															.map(String::trim)
															.collect(Collectors.toList());
			
			final Set<String> differentStrongSpamWords;
			
			differentStrongSpamWords= new HashSet<>(strongSpamWordsList);
			
			errors.state(request, strongSpamWordsList.size() == differentStrongSpamWords.size(), "strongSpamWords", "administrator.system-configuration.form.error.repeated-word");
		}
		
		if(!errors.hasErrors("weakSpamWords")) {
			
			final List<String> weakSpamWordsList = Arrays.asList(entity.getStrongSpamWords().split(","))
															.stream()
															.map(String::trim)
															.collect(Collectors.toList());
			
			final Set<String> differentWeakSpamWords;
			
			differentWeakSpamWords= new HashSet<>(weakSpamWordsList);
			
			errors.state(request, weakSpamWordsList.size() == differentWeakSpamWords.size(), "weakSpamWords", "administrator.system-configuration.form.error.repeated-word");
		}
		
	}
	
	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "systemCurrency", "acceptedCurrencies",
						"strongSpamWords","strongSpamThreshold","weakSpamWords","weakSpamThreshold");
		
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
