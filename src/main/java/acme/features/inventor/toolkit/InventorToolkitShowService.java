package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.helpers.ToolkitHelper;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected ToolkitHelper helper;

	// AbstractShowService<Inventor, Toolkit> ---------------------------
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Toolkit toolkit;
		
		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneToolkitById(masterId);

		result = toolkit != null && (
			!toolkit.isDraftMode() ||
			request.isPrincipal(toolkit.getInventor())
		);
		
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
						"assemblyNotes","link","draftMode");
		
		//Retail Price
		
		final String sc = this.repository.findSystemCurrency();
		
		final Money price = new Money();
		price.setAmount(0.0);
		price.setCurrency(sc);
		
		
		model.setAttribute("retailPrice", this.helper.getToolkitRetailPrice(entity).getAmount()==null?price:this.helper.getToolkitRetailPrice(entity));
		
	}

}
