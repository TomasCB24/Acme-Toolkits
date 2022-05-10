package acme.features.authenticated.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.announcements.Announcement;
import acme.features.administrator.announcement.AdministratorAnnouncementCreateService;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedAnnouncementController extends AbstractController<Authenticated, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedAnnoucementShowService			showService;
	@Autowired
	protected AuthenticatedAnnouncementListRecentService	listRecentService;
	@Autowired
	protected AdministratorAnnouncementCreateService			createService;
	


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-recent", "list", this.listRecentService);
		
	}

}
