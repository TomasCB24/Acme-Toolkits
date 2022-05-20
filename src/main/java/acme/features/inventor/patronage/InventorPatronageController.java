package acme.features.inventor.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageController  extends AbstractController<Inventor, Patronage> {

	@Autowired
	protected InventorPatronageListMineService		listService;
	
	@Autowired
	protected InventorPatronageShowService			showService;
	
	@Autowired
	protected InventorPatronageUpdateService		updateService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-mine","list", this.listService);
		super.addCommand("update", this.updateService);
	}
}
