package acme.features.any.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;
import acme.helpers.ItemHelper;

@Service
public class AnyItemListPublishedService implements AbstractListService<Any, Item> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemRepository repository;
	
	@Autowired
	protected ItemHelper helper;

	// AbstractShowService<Any, Item> ---------------------------
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		
		Collection<Item> result=null;
		
		result = this.repository.findItemsPublished();
		
		return result;
	}
	
	

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "type", "name","code");

		// Retail price
		model.setAttribute("retailPrice", this.helper.getItemRetailPrice(entity));
		
	}

}
