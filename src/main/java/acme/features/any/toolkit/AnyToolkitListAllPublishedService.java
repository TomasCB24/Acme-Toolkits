/*
 * AnyToolkitListAllPublishedService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListAllPublishedService implements AbstractListService<Any, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitRepository repository;

	// AbstractListService<Any, Toolkit> ---------------------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result=null;
		
		result = this.repository.findManyToolkitsPublished();
				
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title");
		
		String payload;
		final StringBuilder  names = new StringBuilder();
		final StringBuilder  codes = new StringBuilder();
		final StringBuilder  descriptions = new StringBuilder();
		final StringBuilder  technologies = new StringBuilder();

		final int toolkitId = entity.getId(); 
		final Collection<Item> items = this.repository.findItemsByToolkitId(toolkitId);

		for(final Item i: items) {

			names.append(i.getName() + " ");
			codes.append(i.getCode() + " ");
			descriptions.append(i.getDescription() + " ");
			technologies.append(i.getTechnology() + " ");
			
		}

		payload = String.format(
			"%s; %s; %s; %s", 
			names.toString(), codes.toString(), descriptions.toString(), technologies.toString());

		model.setAttribute("payload", payload);
		
	}

}
