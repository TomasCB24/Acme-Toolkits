package acme.features.inventor.quantity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.items.ItemType;
import acme.entities.items.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor, Quantity>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;
	
	// AbstractCreateService<Inventor, Quantity> ---------------------------

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Toolkit toolkit;
		Inventor inventor;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		inventor = toolkit.getInventor();
		result = (
			toolkit.isDraftMode() &&
			request.isPrincipal(inventor)
		);
		
		return result;
	}
	
	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;

		final Quantity result;
		final Toolkit toolkit;
		int id;
		
		id = request.getModel().getInteger("masterId");
		
		result = new Quantity();
		toolkit = this.repository.findOneToolkitById(id);
		
		result.setToolkit(toolkit);

		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final String itemCode = String.valueOf(request.getModel().getAttribute("item-code"));
		final Item item = this.repository.findOneItemByCode(itemCode);
		errors.state(request, item!=null, "item-code", "inventor.quantity.form.error.invalid-item");
		entity.setItem(item);
		
		request.bind(entity, errors, "number", "item-code");
		
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("item-code")) {
			
			Collection<Quantity> existing;
			int toolkitId;
			final Collection<String> codes;
			
			toolkitId = request.getModel().getInteger("masterId");
			existing = this.repository.findManyQuantitiesByToolkitId(toolkitId);
			
			codes = existing.stream().map(x->x.getItem().getCode()).collect(Collectors.toList());
				
			errors.state(request, !codes.contains(entity.getItem().getCode()), "item-code", "inventor.quantity.form.error.duplicated-code");
			errors.state(request, !entity.getItem().isDraftMode(), "item-code", "inventor.quantity.form.error.draft-mode");
		
		}
		
		if(!errors.hasErrors("number")) {
			errors.state(request,entity.getItem().getType().equals(ItemType.COMPONENT) || 
								 (entity.getNumber()==1 && entity.getItem().getType().equals(ItemType.TOOL)), "number", "inventor.quantity.form.error.tool");
		}
		
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number", "item-code");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
	
}
