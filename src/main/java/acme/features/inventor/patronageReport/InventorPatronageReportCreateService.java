package acme.features.inventor.patronageReport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractCreateService<Inventor, PatronageReport> ---------------------------

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		final PatronageReport result;

		result = new PatronageReport();
		
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final String code = String.valueOf(request.getModel().getAttribute("patronageCode"));
		final Patronage patronage = this.repository.findOnePatronageByCode(code);
		entity.setPatronage(patronage);

		request.bind(entity, errors, "memorandum", "link", "serialNumber", "patronageCode");
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;

        confirmation = request.getModel().getBoolean("confirmation");
        errors.state(request, confirmation, "confirmation", "inventor.patronage-report.confirmation.error");

        if(!errors.hasErrors("patronageCode")) {
			
			errors.state(request,entity.getPatronage()!=null,"patronageCode", "inventor.quantity.form.error.patronage-can-not-be-null");
		
		}
        
        if(!errors.hasErrors("serialNumber")) {

			PatronageReport existing;

			existing = this.repository.findOnePatronageReportBySerialNumber(entity.getSerialNumber());

			errors.state(request, existing == null || existing.getId() == entity.getId(), "serialNumber", "inventor.patronage-report.form.error.duplicated-code");

		}

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final int inventorId = request.getPrincipal().getActiveRoleId();

		request.unbind(entity, model, "memorandum", "link", "serialNumber");
		model.setAttribute("patronages", this.repository.findManyPublishedPatronagesByInventorId(inventorId));
		model.setAttribute("confirmation", false);
	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
