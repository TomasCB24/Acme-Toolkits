package acme.features.any.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.features.any.item.AnyItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.helpers.MoneyExchangeHelper;
import acme.helpers.ToolkitHelper;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitRepository repository;
	
	@Autowired
	protected MoneyExchangeHelper moneyExchangeService;

	@Autowired
	protected AnyItemRepository anyItemRepository;
	
	@Autowired
	protected ToolkitHelper helper;
	
	
	
	// AbstractShowService<Any, Toolkit> ---------------------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(masterId);
		result = (!toolkit.isDraftMode());
		
		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result=null;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title","description",
						"assemblyNotes","link");
		
		// Inventor full name

		String inventor;
		inventor = entity.getInventor().getIdentity().getFullName();

		model.setAttribute("inventor", inventor);
		
		//Retail Price
	
		model.setAttribute("retailPrice", this.helper.getToolkitRetailPrice(entity));
		
		
		
		
	}

}
