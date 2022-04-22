package acme.features.inventor.item;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
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
    
    String s;
		s = request.getServletRequest().getRequestURI();
    
    if(s.contains("/inventor/item/list-mine")){
     
      int masterId;
      Item item;
      Inventor inventor;
      Principal principal;

      masterId = request.getModel().getInteger("id");
      item = this.repository.findOneItemById(masterId);
      inventor = item.getInventor();
      principal = request.getPrincipal();
      result = (
        inventor.getUserAccount().getId() == principal.getAccountId());
      
      return result;
      
    } else {
      int itemId;
      Collection<Toolkit> publishedToolkits;
      Collection<Toolkit> toolkits;

      itemId = request.getModel().getInteger("id");
      publishedToolkits = this.repository.findManyPublishedToolkitsByItemId(itemId);
      toolkits = this.repository.findManyToolkitsByItemId(itemId);

      Principal principal;
      principal = request.getPrincipal();

      Item item;
      item = this.repository.findOneItemById(itemId);

      result = (!publishedToolkits.isEmpty() || 
          !(toolkits.stream().filter(t->t.getInventor().getUserAccount().getId() == principal.getAccountId())
                    .collect(Collectors.toList()).isEmpty()) ||
          (item.getInventor().getUserAccount().getId() == principal.getAccountId())

      );

      return result;
      
    }
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
