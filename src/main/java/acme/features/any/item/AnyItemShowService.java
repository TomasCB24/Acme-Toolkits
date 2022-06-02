
package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.helpers.ItemHelper;

@Service
public class AnyItemShowService implements AbstractShowService<Any, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemRepository repository;
	
	@Autowired
	protected ItemHelper helper;

	// AbstractShowService<Any, Toolkit> ---------------------------
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int itemId;
		Item item;
	
		
		itemId = request.getModel().getInteger("id");
  
		item = this.repository.findOneItemById(itemId);
		
		result = item != null && !item.isDraftMode();
		
		return result;
		
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		Item result=null;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "type", "name","code",
			"technology","description","link");
		
		// Inventor full name

		String inventor;
		inventor = entity.getInventor().getIdentity().getFullName();

		model.setAttribute("inventor", inventor);
		
		// Retail price
		model.setAttribute("retailPrice", this.helper.getItemRetailPrice(entity));
		
	}


	


}
