package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorItemListService implements AbstractListService<Inventor, Item>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	// AbstractListService<Inventor, Item> ---------------------------
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		result = (toolkit != null && 
			(!toolkit.isDraftMode() || request.isPrincipal(toolkit.getInventor()))
			);
		
		return result;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		
		Collection<Item> result=null;
		int id;
		
		id = request.getModel().getInteger("masterId");
		result = this.repository.findManyItemsByMasterId(id);
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Collection<Item> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;

		int masterId;
		final Toolkit toolkit;
		final boolean showCreate;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		showCreate = (toolkit.isDraftMode() && request.isPrincipal(toolkit.getInventor()));

		model.setAttribute("masterId", masterId);
		model.setAttribute("showCreate", showCreate);
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "type", "name","retailPrice");
		
		//Quantity
		
		int toolkitId;
		toolkitId = request.getModel().getInteger("masterId");

		int itemId;
		itemId = entity.getId();
		
		int quantity;
		quantity = this.repository.findQuantityForItemInToolkit(toolkitId, itemId);
		
		model.setAttribute("quantity", quantity);
		
	}

}
