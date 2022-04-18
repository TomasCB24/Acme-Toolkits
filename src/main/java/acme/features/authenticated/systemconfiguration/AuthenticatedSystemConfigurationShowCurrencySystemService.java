package acme.features.authenticated.systemconfiguration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedSystemConfigurationShowCurrencySystemService implements AbstractShowService<Authenticated, SystemConfiguration>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedSystemConfigurationRepository repository;

	// AbstractShowService<Authenticated, SystemConfiguration> ---------------------------
	
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
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "systemCurrency", "acceptedCurrencies");
		
	}

}
