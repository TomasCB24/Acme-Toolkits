/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Status;
import acme.forms.AdminDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdminDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, AdministratorDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<AdminDashboard> request) {
		assert request != null;

		return true;
	}
	
	private Map<Pair<String, Status>, Double> calculateComplexMetric(final List<String> metricByCurrency) {
		final Map<Pair<String, Status>, Double> r = new HashMap<Pair<String, Status>, Double>();

		for(int i=0; i<metricByCurrency.size(); i++) {
			final String queryRes = metricByCurrency.get(i);
			final String[] subs = queryRes.split(",");
			final Double key = Double.parseDouble(subs[1]);
			final String currency = subs[0];
			final Status status = Status.valueOf(subs[2]);
			final Pair<String, Status> res = Pair.of(currency, status);
			r.put(res, key);
		}

		return r;
	}
	
	private Map<String, Double> calculateComplexMetricSimple(final List<String> metricByCurrency) {
		final Map<String, Double> r = new HashMap<String,Double>();

		
		for(int j=0; j<metricByCurrency.size(); j++) {
			final String linea= metricByCurrency.get(j);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			r.put(divisa, key);
			
		 }

		return r;
	}
	
	private Map<Pair<String, String>, Double> calculateComplexMetricDouble(final List<String> metricByCurrency) {
		final Map<Pair<String, String>, Double> r = new HashMap<Pair<String, String>, Double>();

		for(int j=0; j<metricByCurrency.size(); j++) {
			final String linea= metricByCurrency.get(j);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			r.put(res, key);
		}

		return r;
	}
	
	//PATRONAGES
	public Map<Pair<String, Status>, Double> calculateAverageBudgetOfPatronages() {
		final List<String> averageBudgetByCurrency = this.repository.averageBudgetPatronages();
		return this.calculateComplexMetric(averageBudgetByCurrency);
	}

	public Map<Pair<String, Status>, Double> calculateDeviationBudgetOfPatronages() {
		final List<String> deviationBudgetByCurrency = this.repository.deviationBudgetPatronages();
		return this.calculateComplexMetric(deviationBudgetByCurrency);
	}

	public Map<Pair<String, Status>, Double> calculateMinBudgetOfPatronages() {
		final List<String> minBudgetByCurrency = this.repository.minimumBudgetPatronages();
		return this.calculateComplexMetric(minBudgetByCurrency);
	}
	
	public Map<Pair<String, Status>, Double> calculateMaxBudgetOfPatronages() {
		final List<String> maxBudgetByCurrency = this.repository.maximumBudgetPatronages();
		return this.calculateComplexMetric(maxBudgetByCurrency);
	}
	
	//TOOLS
	
	public Map<String, Double> calculateAverageOfToolsRetailPrice() {
		final List<String> averageOfToolsRetailPrice = this.repository.averageOfToolsRetailPrice();
		return this.calculateComplexMetricSimple(averageOfToolsRetailPrice);
	}
	
	public Map<String, Double> calculateDeviationOfToolsRetailPrice() {
		final List<String> deviationOfToolsRetailPrice = this.repository.deviationOfToolsRetailPrice();
		return this.calculateComplexMetricSimple(deviationOfToolsRetailPrice);
	}
	
	public Map<String, Double> calculateMinimumOfToolsRetailPrice() {
		final List<String> minOfToolsRetailPrice = this.repository.minimumOfToolsRetailPrice();
		return this.calculateComplexMetricSimple(minOfToolsRetailPrice);
	}
	
	public Map<String, Double> calculateMaxOfToolsRetailPrice() {
		final List<String> maxOfToolsRetailPrice = this.repository.maximumOfToolsRetailPrice();
		return this.calculateComplexMetricSimple(maxOfToolsRetailPrice);
	}
	
	//COMPONENTS
	
	public Map<Pair<String, String>, Double> calculateAverageOfComponentsRetailPrice() {
		final List<String> averageOfComponentsRetailPrice = this.repository.averageOfComponentsRetailPrice();
		return this.calculateComplexMetricDouble(averageOfComponentsRetailPrice);
	}
	
	public Map<Pair<String, String>, Double> calculateDeviationOfComponentsRetailPrice() {
		final List<String> deviationOfComponentsRetailPrice = this.repository.deviationOfComponentsRetailPrice();
		return this.calculateComplexMetricDouble(deviationOfComponentsRetailPrice);
	}
	
	public Map<Pair<String, String>, Double> calculateMinimumOfComponentsRetailPrice() {
		final List<String> minOfComponentsRetailPrice = this.repository.minimumOfComponentsRetailPrice();
		return this.calculateComplexMetricDouble(minOfComponentsRetailPrice);
	}
	
	public Map<Pair<String, String>, Double> calculateMaxOfComponentsRetailPrice() {
		final List<String> maxOfComponentsRetailPrice = this.repository.maximumOfComponentsRetailPrice();
		return this.calculateComplexMetricDouble(maxOfComponentsRetailPrice);
	}

	@Override
	public AdminDashboard findOne(final Request<AdminDashboard> request) {
		assert request != null;

		final AdminDashboard result;
		int totalNumberOfComponents;
		final int totalNumberOfTools;
		int totalNumberOfProposedPatronages;
		int totalNumberOfAcceptedPatronages;
		int totalNumberOfDeniedPatronages;
		
		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		totalNumberOfTools = this.repository.totalNumberOfTools();
		totalNumberOfProposedPatronages = this.repository.totalNumberOfProposedPatronages();
		totalNumberOfAcceptedPatronages = this.repository.totalNumberOfAcceptedPatronages();
		totalNumberOfDeniedPatronages = this.repository.totalNumberOfDeniedPatronages();
		
	
		result = new AdminDashboard();
		
		//TOOLS
		result.setTotalNumberOftools(totalNumberOfTools);
		result.setAverageOfToolsRetailPrice(this.calculateAverageOfToolsRetailPrice());
		result.setDeviationOfToolsRetailPrice(this.calculateDeviationOfToolsRetailPrice());
		result.setMinimumOfToolsRetailPrice(this.calculateMinimumOfToolsRetailPrice());
		result.setMaximumOfToolsRetailPrice(this.calculateMaxOfToolsRetailPrice());
		
		//COMPONENTS
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setAverageOfComponentsRetailPrice(this.calculateAverageOfComponentsRetailPrice());
		result.setDeviationOfComponentsRetailPrice(this.calculateDeviationOfComponentsRetailPrice());
		result.setMaximumOfComponentsRetailPrice(this.calculateMinimumOfComponentsRetailPrice());
		result.setMinimumOfComponentsRetailPrice(this.calculateMaxOfComponentsRetailPrice());
		
		//PATRONAGE
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setAverageBudgetPatronages(this.calculateAverageBudgetOfPatronages());
		result.setDeviationBudgetPatronages(this.calculateDeviationBudgetOfPatronages());
		result.setMinimumBudgetPatronages(this.calculateMinBudgetOfPatronages());
		result.setMaximumBudgetPatronages(this.calculateMaxBudgetOfPatronages());

		return result;
	}

	@Override
	public void unbind(final Request<AdminDashboard> request, final AdminDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"totalNumberOfComponents","averageOfComponentsRetailPrice","deviationOfComponentsRetailPrice","maximumOfComponentsRetailPrice","minimumOfComponentsRetailPrice", // 
			"totalNumberOfProposedPatronages","totalNumberOfAcceptedPatronages","averageOfToolsRetailPrice", "deviationOfToolsRetailPrice","minimumOfToolsRetailPrice","maximumOfToolsRetailPrice",//
			"totalNumberOfDeniedPatronages","averageBudgetPatronages","deviationBudgetPatronages","minimumBudgetPatronages","maximumBudgetPatronages");
		model.setAttribute("tools",entity.getTotalNumberOftools());
	}

}
