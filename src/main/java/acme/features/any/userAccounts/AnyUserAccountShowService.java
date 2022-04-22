package acme.features.any.userAccounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyUserAccountRepository repository;




	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;
		
		UserAccount result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneUserAccountById(id);
		result.getRoles().forEach(r -> {});
		
		return result;
	}


	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String name = entity.getIdentity().getName();
		model.setAttribute("name", name);
		final String surname = entity.getIdentity().getSurname();
		model.setAttribute("surname", surname);
		final String email = entity.getIdentity().getEmail();
		model.setAttribute("email", email);
		final String roles = entity.getAuthorityString();
		model.setAttribute("roles", roles);
		
		request.unbind(entity, model, "username");
	}

}
