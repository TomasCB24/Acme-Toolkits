package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.helpers.SpamHelper;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected SpamHelper helper;

	// AbstractUpdateService<Inventor, Toolkit> ---------------------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(masterId);

		result = toolkit != null && (
			toolkit.isDraftMode() &&
			request.isPrincipal(toolkit.getInventor())
		);
		
		return result;
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title","description","assemblyNotes","link");
		
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			
			Toolkit existing;
			
			existing = this.repository.findOneToolkitByCode(entity.getCode());
			
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.toolkit.form.error.duplicated-code");
		}
		
		if(!errors.hasErrors("title")) {
			final SystemConfiguration sc = this.repository.findSystemConfiguration();
			final boolean spamFree = this.helper.spamChecker(entity.getTitle(),sc.getStrongSpamWords(),sc.getWeakSpamWords(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "title", "form.error.spam");
		}
		
		if(!errors.hasErrors("description")) {
			final SystemConfiguration sc = this.repository.findSystemConfiguration();
			final boolean spamFree = this.helper.spamChecker(entity.getDescription(),sc.getStrongSpamWords(),sc.getWeakSpamWords(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "description", "form.error.spam");
		}
		
		if(!errors.hasErrors("assemblyNotes")) {
			final SystemConfiguration sc = this.repository.findSystemConfiguration();
			final boolean spamFree = this.helper.spamChecker(entity.getAssemblyNotes(),sc.getStrongSpamWords(),sc.getWeakSpamWords(),sc.getStrongSpamThreshold(),sc.getWeakSpamThreshold());
			errors.state(request, spamFree, "assemblyNotes", "form.error.spam");
		}
		
		Collection<Item> items;
		items = this.repository.findManyItemsByToolkitId(entity.getId());
		
		errors.state(request,!items.isEmpty(), "*", "inventor.toolkit.form.error.no-item");
		
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title","description","assemblyNotes","link", "draftMode");
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setDraftMode(false);
		this.repository.save(entity);
		
	}
	
	
}
