package acme.features.authenticated.inventor;

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
import acme.roles.Inventor;

@Service
public class AuthenticatedInventorUpdateService implements AbstractUpdateService<Authenticated, Inventor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedInventorRepository repository;

	// AbstractUpdateService<Authenticated, Inventor> interface -----------------


	@Override
	public boolean authorise(final Request<Inventor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Inventor> request, final Inventor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Inventor> request, final Inventor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "company", "statement", "link");
	}

	@Override
	public void unbind(final Request<Inventor> request, final Inventor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "statement", "link");
	}

	@Override
	public Inventor findOne(final Request<Inventor> request) {
		assert request != null;

		Inventor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneInventorByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Inventor> request, final Inventor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Inventor> request, final Response<Inventor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
	
}
	
