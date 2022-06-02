package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.items.ItemType;
import acme.entities.items.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor, Quantity>{

	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractUpdateService<Inventor, Quantity> ---------------------------
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		boolean result;
		int id;
		Quantity quantity;

		id = request.getModel().getInteger("id");
		quantity = this.repository.findOneQuantityById(id);

		result = quantity != null && (
			quantity.getToolkit().isDraftMode() &&
			request.isPrincipal(quantity.getToolkit().getInventor())
		);
		
		return result;
	}
	
	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		Quantity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final String itemCode = String.valueOf(request.getModel().getAttribute("itemCode"));
		final Item item = this.repository.findOneItemByCode(itemCode);
		entity.setItem(item);
		
		request.bind(entity, errors, "number", "itemCode");
		
	}
	
	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("number")) {
			
			int id;
			Quantity quantity;
			Toolkit toolkit;

			id = request.getModel().getInteger("id");
			toolkit = this.repository.findOneQuantityById(id).getToolkit();
			quantity = this.repository.findOneQuantityByItemId(toolkit.getId(), entity.getItem().getCode());

			errors.state(request,entity.getItem().getType().equals(ItemType.COMPONENT) || 
						         (entity.getNumber()==1 && entity.getItem().getType().equals(ItemType.TOOL) && quantity == null), 
						         "number", "inventor.quantity.form.error.tool");
		}
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number");
		model.setAttribute("itemCode", entity.getItem().getCode());
		
	}	

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
