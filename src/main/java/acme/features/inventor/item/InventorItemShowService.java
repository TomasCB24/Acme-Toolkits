package acme.features.inventor.item;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	// AbstractShowService<Inventor, Item> ---------------------------
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
        int itemId;
        Item item;
        Inventor inventor;

        itemId = request.getModel().getInteger("id");
        item = this.repository.findOneItemById(itemId);
        inventor = item.getInventor();
        result = (!item.isDraftMode() ||
        			request.isPrincipal(inventor));
      
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
			"technology","description","retailPrice","link");
		
		// Inventor full name
		
		String inventor;
		inventor = entity.getInventor().getIdentity().getFullName();
		
		model.setAttribute("inventor", inventor);
		
	}

}
