package acme.features.inventor.item;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.items.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item>{

  @Autowired
	protected InventorItemListService				listService;
  
	@Autowired
	protected InventorItemListMineService listMineService;
	
	@Autowired
	protected InventorItemShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-mine","list", this.listMineService);
    super.addCommand("list", this.listService);
    
	}
}




