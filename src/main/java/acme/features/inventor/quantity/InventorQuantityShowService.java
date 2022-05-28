package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor, Quantity>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractShowService<Inventor, Quantity> ---------------------------
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Quantity quantity;
		Inventor inventor;

		masterId = request.getModel().getInteger("id");
		quantity = this.repository.findOneQuantityById(masterId);
		assert quantity != null;
		inventor = quantity.getToolkit().getInventor();
		result = (
			quantity.getToolkit().isDraftMode() &&
			request.isPrincipal(inventor)
		);
		
		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		Quantity result=null;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "number");
		
		model.setAttribute("itemCode", entity.getItem().getCode());
		model.setAttribute("items", this.repository.findManyPublishedItems());

		
	}

}
