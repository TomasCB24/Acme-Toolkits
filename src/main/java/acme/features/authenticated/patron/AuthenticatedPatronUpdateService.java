package acme.features.authenticated.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class AuthenticatedPatronUpdateService implements AbstractUpdateService<Authenticated, Patron> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedPatronRepository repository;

	// AbstractUpdateService<Authenticated, Patron> interface -----------------


	@Override
	public boolean authorise(final Request<Patron> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Patron> request, final Patron entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Patron> request, final Patron entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "company", "statement", "link");
	}

	@Override
	public void unbind(final Request<Patron> request, final Patron entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "statement", "link");
	}

	@Override
	public Patron findOne(final Request<Patron> request) {
		assert request != null;

		Patron result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOnePatronByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Patron> request, final Patron entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Patron> request, final Response<Patron> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	
}
	
