package acme.features.patron.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageController extends AbstractController<Patron, Patronage> {

	@Autowired
	protected PatronPatronageListMineService		listService;

	@Autowired
	protected PatronPatronageShowService			showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-mine","list", this.listService);
	}
}