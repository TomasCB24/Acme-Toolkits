package acme.features.patron.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}
	
	private Map<Pair<String, Status>, Double> calculateComplexMetric(final List<String> metricByCurrency) {
		final Map<Pair<String, Status>, Double> result = new HashMap<Pair<String, Status>, Double>();
		
		for(int i=0; i<metricByCurrency.size(); i++) {
			final String queryRes = metricByCurrency.get(i);
			final String[] subs = queryRes.split(",");
			final Double key = Double.parseDouble(subs[1]);
			final String currency = subs[0];
			final Status status = Status.valueOf(subs[2]);
			final Pair<String, Status> res = Pair.of(currency, status);
			result.put(res, key);
		}
		
		return result;
	}
	
	public Map<Pair<String, Status>, Double> calculateAverageBudgetOfPatronages() {
		final List<String> averageBudgetByCurrency = this.repository.averageBudgetByCurrency();
		return this.calculateComplexMetric(averageBudgetByCurrency);
	}
	
	public Map<Pair<String, Status>, Double> calculateDeviationBudgetOfPatronages() {
		final List<String> deviationBudgetByCurrency = this.repository.deviationBudgetByCurrency();
		return this.calculateComplexMetric(deviationBudgetByCurrency);
	}
	
	public Map<Pair<String, Status>, Double> calculateMinBudgetOfPatronages() {
		final List<String> minBudgetByCurrency = this.repository.minBudgetByCurrency();
		return this.calculateComplexMetric(minBudgetByCurrency);
	}
	
	public Map<Pair<String, Status>, Double> calculateMaxBudgetOfPatronages() {
		final List<String> maxBudgetByCurrency = this.repository.maxBudgetByCurrency();
		return this.calculateComplexMetric(maxBudgetByCurrency);
	}
	
	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {

		assert request != null;
		
		final PatronDashboard result=new PatronDashboard();
		
		final int totalNumberOfProposedPatronages=this.repository.totalNumberOfProposedPatronages();
		final int totalNumberOfAcceptedPatronages=this.repository.totalNumberOfAcceptedPatronages();
		final int totalNumberOfDeniedPatronages=this.repository.totalNumberOfDeniedPatronages();
		final Map<Pair<String, Status>, Double> averageBudgetOfPatronages = this.calculateAverageBudgetOfPatronages();
		final Map<Pair<String, Status>, Double> deviationBudgetOfPatronages = this.calculateDeviationBudgetOfPatronages();
		final Map<Pair<String, Status>, Double> minBudgetOfPatronages = this.calculateMinBudgetOfPatronages();
		final Map<Pair<String, Status>, Double> maxBudgetOfPatronages = this.calculateMaxBudgetOfPatronages();

		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
		result.setAverageBudgetOfPatronages(averageBudgetOfPatronages);
		result.setDeviationBudgetOfPatronages(deviationBudgetOfPatronages);
		result.setMinBudgetOfPatronages(minBudgetOfPatronages);
		result.setMaxBudgetOfPatronages(maxBudgetOfPatronages);
		
		return result;
	}
	
	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfProposedPatronages", 
			"totalNumberOfAcceptedPatronages", "totalNumberOfDeniedPatronages", 
			"averageBudgetOfPatronages", "deviationBudgetOfPatronages", 
			"minBudgetOfPatronages", "maxBudgetOfPatronages");
	}

}
