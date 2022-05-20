package acme.features.inventor.item;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item>{
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected InventorItemRepository repository;

	// AbstractUpdateService<Inventor, Item> ---------------------------
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		int itemId;
		final Item item;
		Inventor inventor;
		

		itemId = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(itemId);
		inventor = item.getInventor();
		result = (
			item.isDraftMode() &&
			request.isPrincipal(inventor)
		);
		
		return result;
	}
	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "type", "name","code","technology","description","retailPrice","link");
		
	}
	
	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			
			Item existing;
			
			existing = this.repository.findOneItemByCode(entity.getCode());
			
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.item.form.error.duplicated-code");
		}
		
		if(!errors.hasErrors("retailPrice")) {
			final String[] currencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			final Set<String> systemCurrencies = new HashSet<>();
			for(final String currency:currencies) {
				systemCurrencies.add(currency);
			} 
			errors.state(request, entity.getRetailPrice().getAmount()>0, "retailPrice", "inventor.item.form.error.negative-money");
			errors.state(request, systemCurrencies.contains(entity.getRetailPrice().getCurrency()), "retailPrice", "inventor.item.form.error.invalid-money");
		}
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name","code","technology","description","retailPrice","link", "draftMode");

	}	

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
