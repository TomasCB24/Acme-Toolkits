package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.helpers.PatronageHelper;
import acme.roles.Inventor;
import acme.roles.Patron;


@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected PatronageHelper helper;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		boolean result;
		int masterId;
		Patronage patronage;
		Patron patron;
		
		masterId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(masterId);
		patron = patronage.getPatron();
		
		result = patron != null &&(
			request.isPrincipal(patron));
			
		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		Patronage result;
		int id;
		
		id=request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "code", "legalStuff", "creationDate", "initialPeriodDate", "finalPeriodDate", "link", "draftMode");
		

		//Patron details:
		final Patronage patronage = this.repository.findOnePatronageById(entity.getId());
		final Inventor inventor = patronage.getInventor();
		final String inventorUsername = inventor.getUserAccount().getUsername();
		final String company = inventor.getCompany();
		final String statement = inventor.getStatement();
		final String inventorLink = inventor.getLink();
		
		model.setAttribute("company", company);
		model.setAttribute("statement", statement); 
		model.setAttribute("inventorLink", inventorLink);
		model.setAttribute("inventor", inventorUsername);
		model.setAttribute("status", entity.getStatus().toString());
		
		// Budget
		model.setAttribute("budget", this.helper.getPatronageBudget(entity));
		
		
	}
	
}
