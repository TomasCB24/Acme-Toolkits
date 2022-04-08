/*
 * AuthenticatedConsumerController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.announcement;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.announcements.Announcement;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;

@Controller
@RequestMapping("/authenticated/announcement/")
public class AuthenticatedAnnouncementController extends AbstractController<Authenticated, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedAnnoucementShowService			showService;
	@Autowired
	protected AuthenticatedAnnouncementListRecentService	listRecentService;
	


	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list-recent", "list", this.listRecentService);
	}

}
