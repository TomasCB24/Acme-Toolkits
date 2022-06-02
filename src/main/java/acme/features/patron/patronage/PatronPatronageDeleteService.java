package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;

@Service
public class PatronPatronageDeleteService  implements AbstractDeleteService<Patron, Patronage>{

	@Autowired
	protected PatronPatronageRepository repository;
	
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
		
		result = patron != null && (
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
		
		request.bind(entity, errors, "code", "status","legalStuff","budget","creationDate", "initialPeriodDate", "finalPeriodDate", "link");
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "status","legalStuff","budget","creationDate", "initialPeriodDate", "finalPeriodDate", "link");
		
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
		
	}

	@Override
	public void delete(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		Collection<PatronageReport> patronageReports;

		patronageReports = this.repository.findManyPatronageReportsByPatronageId(entity.getId());
		for (final PatronageReport patronageReport : patronageReports) {
			this.repository.delete(patronageReport);
		}
		this.repository.delete(entity);
	}

}
