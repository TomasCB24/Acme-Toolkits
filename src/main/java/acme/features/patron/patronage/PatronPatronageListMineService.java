package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageListMineService implements AbstractListService<Patron, Patronage>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronPatronageRepository repository;
		
		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;
			return true;
		}

		@Override
		public Collection<Patronage> findMany(final Request<Patronage> request) {
			assert request != null;
			Collection<Patronage> result;
			Principal principal;

			principal = request.getPrincipal();
			result = this.repository.findManyPatronagesByPatronId(principal.getActiveRoleId());
			
			return result;
		}

		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "code", "legalStuff", "creationDate");
			
		}
}
