package acme.features.inventor.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportShowService implements AbstractShowService<Inventor, PatronageReport>{
	
	@Autowired
	protected InventorPatronageReportRepository repository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		boolean result;
		int masterId;
		PatronageReport patronageReport;
		Inventor inventor;
		Principal principal;
		
		masterId = request.getModel().getInteger("id");
		patronageReport = this.repository.findOnePatronageReportById(masterId);
		inventor = patronageReport.getPatronage().getInventor();
		principal = request.getPrincipal();
		result = inventor.getUserAccount().getId()==principal.getAccountId();
		return result;
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;
		PatronageReport result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageReportById(id);
		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "memorandum", "creationMoment", "link");
		final PatronageReport patronageReport = this.repository.findOnePatronageReportById(entity.getId());
		final String sequenceNumber = patronageReport.sequenceNumber();
		model.setAttribute("sequenceNumber", sequenceNumber);
		
	}
	

}