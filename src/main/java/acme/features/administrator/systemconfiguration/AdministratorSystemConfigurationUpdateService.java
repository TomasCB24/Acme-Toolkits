package acme.features.administrator.systemconfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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

	// AbstractUpdateService<Administrator, SystemConfiguration> ---------------------------

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		
		SystemConfiguration result=null;
		
		final Optional<SystemConfiguration> scOpt = this.repository.findSystemConfiguration().stream().findFirst();
		
		if(scOpt.isPresent()) {
			result = scOpt.get();
		}
		
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
