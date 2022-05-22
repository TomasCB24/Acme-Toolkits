package acme.features.inventor.item;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.helpers.ItemHelper;
import acme.roles.Inventor;

@Service
public class InventorItemListMineService implements AbstractListService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected ItemHelper helper;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		Collection<Item> result;
		Principal principal;
		
		principal = request.getPrincipal();
		result = this.repository.findManyItemsByInventorId(principal.getActiveRoleId());
		
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "type", "name");
		
		
		// Retail price
		model.setAttribute("retailPrice", this.helper.getItemRetailPrice(entity));
	}

}
