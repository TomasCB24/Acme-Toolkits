/*
 * AuthenticatedConsumerUpdateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.consumer;

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
import acme.roles.Consumer;

@Service
public class AuthenticatedConsumerUpdateService implements AbstractUpdateService<Authenticated, Consumer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedConsumerRepository repository;

	// AbstractUpdateService<Authenticated, Consumer> interface -----------------


	@Override
	public boolean authorise(final Request<Consumer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Consumer> request, final Consumer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Consumer> request, final Consumer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "company", "sector");
	}

	@Override
	public void unbind(final Request<Consumer> request, final Consumer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "sector");
	}

	@Override
	public Consumer findOne(final Request<Consumer> request) {
		assert request != null;

		Consumer result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneConsumerByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Consumer> request, final Consumer entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Consumer> request, final Response<Consumer> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
