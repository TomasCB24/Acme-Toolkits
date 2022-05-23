package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.features.any.toolkit.AnyToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService  implements AbstractUpdateService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected AnyToolkitRepository anyToolKitRepository;
	

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
		result = (
			patronage.isDraftMode() &&
			request.isPrincipal(patron)
		);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final String inventorUsername = String.valueOf(request.getModel().getAttribute("inventor"));
		final Inventor inventor = this.repository.findOneInventorByUserName(inventorUsername);
		errors.state(request, inventor!=null, "inventor", "patron.patronage.form.error.inventor-not-found");
		entity.setInventor(inventor);
		
		request.bind(entity, errors, "code", "status","legalStuff","budget","creationDate", "initialPeriodDate", "finalPeriodDate", "link");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "code", "status","legalStuff","budget","creationDate", "initialPeriodDate", "finalPeriodDate", "link", "draftMode");
		final Patronage patronage = this.repository.findOnePatronageById(entity.getId());
		final Inventor inventor = patronage.getInventor();
		final String inventorUsername = inventor.getUserAccount().getUsername();
		model.setAttribute("inventor", inventorUsername);
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			
			Patronage existing;
			
			existing = this.repository.findOnePatronageByCode(entity.getCode());
			
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "patron.patronage.form.error.duplicated-code");
		}
		
		if (!errors.hasErrors("initialPeriodDate")) {
			Date minimumInitialDate;

			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(entity.getCreationDate());
			calendar.add(Calendar.MONTH, 1);
			minimumInitialDate = calendar.getTime();
			errors.state(request, entity.getInitialPeriodDate().after(minimumInitialDate), "initialPeriodDate", "patron.patronage.form.error.too-close-initial");
		}
		
		if (!errors.hasErrors("finalPeriodDate")) {
			Date minimumFinalDate;

			final Calendar calendar = Calendar.getInstance();
			calendar.setTime(entity.getInitialPeriodDate());
			calendar.add(Calendar.MONTH, 1);
			minimumFinalDate = calendar.getTime();
			errors.state(request, entity.getFinalPeriodDate().after(minimumFinalDate), "finalPeriodDate", "patron.patronage.form.error.too-close-final");
		}
		
		if(!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-amount");
			final String currency = entity.getBudget().getCurrency();
			final String[] currencies = this.anyToolKitRepository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			final Set<String> set = new HashSet<>();

			Collections.addAll(set, currencies);
			
			final boolean res = set.contains(currency);
			
			errors.state(request, res, "budget", "patron.patronage.form.error.unknown-currency");
		}
		
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	

}
