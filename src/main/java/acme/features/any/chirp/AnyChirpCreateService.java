package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirps.Chirp;
import acme.entities.configuration.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import acme.helpers.SpamHelper;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyChirpRepository repository;
	
	@Autowired
	protected SpamHelper helper;

	// AbstractCreateService<Administrator, Shout> interface --------------


	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;

		return true;
	}

	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;

		Chirp result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Chirp();		
		result.setCreationMoment(moment);
		
		return result;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "author", "body", "email");
	}

	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;

        confirmation = request.getModel().getBoolean("confirmation");
        errors.state(request, confirmation, "confirmation", "any.chirp.confirmation.error");
        
		if(!errors.hasErrors("title")) {
			final SystemConfiguration sc = this.repository.findSystemConfiguration();
			final boolean spamFree = this.helper.spamChecker(entity.getTitle(),sc.getStrongSpamWords(),sc.getWeakSpamWords(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "title", "form.error.spam");
		}
		
		if(!errors.hasErrors("body")) {
			final SystemConfiguration sc = this.repository.findSystemConfiguration();
			final boolean spamFree = this.helper.spamChecker(entity.getBody(),sc.getStrongSpamWords(),sc.getWeakSpamWords(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "body", "form.error.spam");
		}
		


	}

	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "author", "body", "email");
		model.setAttribute("confirmation", false);
	}

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
