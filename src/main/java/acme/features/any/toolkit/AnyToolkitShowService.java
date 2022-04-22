package acme.features.any.toolkit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitRepository repository;

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
		
		final Money retailPrice = new Money();
		Double amount;
		String currency;
		
		amount = this.repository.computeRetailPriceByToolkitId(entity.getId());
		
		currency = null;
		
		final Optional<String> scOpt = this.repository.findSystemCurrency().stream().findFirst();
		
		if(scOpt.isPresent()) {
			currency = scOpt.get();
		}
		
		retailPrice.setAmount(amount);
		retailPrice.setCurrency(currency);
		
		model.setAttribute("retailPrice", retailPrice);
		
		
	}

}