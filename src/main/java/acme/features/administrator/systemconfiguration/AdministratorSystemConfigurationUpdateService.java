package acme.features.administrator.systemconfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
			
			final List<String> aceptedCurrenciesList = Arrays.asList(entity.getAcceptedCurrencies().split(","));
			
			errors.state(request, aceptedCurrenciesList.contains(entity.getSystemCurrency()), "systemCurrency", "administrator.system-configuration.form.error.system-currency");
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
