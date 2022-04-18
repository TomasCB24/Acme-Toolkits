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

		final Map<Pair<String, String>, Double> averageOfComponentsRetailPrice =  new HashMap<Pair<String, String>, Double>();	
		final Map<Pair<String, String>, Double> deviationOfComponentsRetailPrice =  new HashMap<Pair<String, String>, Double>();		
		final Map<Pair<String, String>, Double> minimumOfComponentsRetailPrice =  new HashMap<Pair<String, String>, Double>();		
		final Map<Pair<String, String>, Double> maximumOfComponentsRetailPrice =  new HashMap<Pair<String, String>, Double>();
		final Map<String,Double> averageRetailPriceOfTools= new HashMap<String, Double>();
		final Map<String,Double> deviationRetailPriceOfTools= new HashMap<String, Double>();
		final Map<String,Double> minRetailPriceOfTools= new HashMap<String, Double>();
		final Map<String,Double> maxRetailPriceOfTools= new HashMap<String, Double>();
		final Map<Status,Double> averageBudgetByStatus= new HashMap<Status, Double>();
		final Map<Status,Double> deviationBudgetByStatus= new HashMap<Status, Double>();
		final Map<Status,Double> minBudgetByStatus= new HashMap<Status, Double>();
		final Map<Status,Double> maxBudgetByStatus= new HashMap<Status, Double>();
		
		int i = 0;
		int tamaño = this.repository.averageOfComponentsRetailPrice().size();
		while(i<tamaño) {
			final String linea= this.repository.averageOfComponentsRetailPrice().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			averageOfComponentsRetailPrice.put(res, key);
			i++;
		}
		i=0;
		tamaño = this.repository.deviationOfComponentsRetailPrice().size();
		while(i<tamaño) {
			final String linea= this.repository.deviationOfComponentsRetailPrice().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			deviationOfComponentsRetailPrice.put(res, key);
			i++;
		}
		i=0;
		tamaño = this.repository.minimumOfComponentsRetailPrice().size();
		while(i<tamaño) {
			final String linea= this.repository.minimumOfComponentsRetailPrice().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			minimumOfComponentsRetailPrice.put(res, key);
			i++;
		}
		i=0;
		tamaño = this.repository.maximumOfComponentsRetailPrice().size();
		while(i<tamaño) {
			final String linea= this.repository.maximumOfComponentsRetailPrice().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[2]);
			final String divisa=sub[0];
			final String tecnologia= sub[1];
			final Pair<String, String> res=Pair.of(divisa, tecnologia);
			maximumOfComponentsRetailPrice.put(res, key);
			i++;
		 }
		i=0;
		tamaño = this.repository.averageOfToolsRetailPrice().size();
		while(i<tamaño) {
			final String linea= this.repository.averageOfToolsRetailPrice().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			averageRetailPriceOfTools.put(divisa, key);
			i++;
		 }
	i=0;
	tamaño = this.repository.deviationOfToolsRetailPrice().size();
		while(i<tamaño) {
			final String linea= this.repository.deviationOfToolsRetailPrice().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			deviationRetailPriceOfTools.put(divisa, key);
			i++;
		 }
		i=0;
		
		tamaño = this.repository.minimumOfToolsRetailPrice().size();
		while(i<tamaño) {
			final String linea= this.repository.minimumOfToolsRetailPrice().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			minRetailPriceOfTools.put(divisa, key);
			i++;
		 }
		i=0;
		
		tamaño = this.repository.maximumOfToolsRetailPrice().size();
		while(i<tamaño) {
			final String linea= this.repository.maximumOfToolsRetailPrice().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String divisa=sub[0];
			maxRetailPriceOfTools.put(divisa, key);
			i++;
		 }
		i=0;
		tamaño = this.repository.averageBudgetPatronages().size();
		while(i<tamaño) {
			final String linea= this.repository.averageBudgetPatronages().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String status=sub[2];
			if(status.equals(acme.entities.patronages.Status.ACCEPTED.toString())) {
				averageBudgetByStatus.put(acme.entities.patronages.Status.ACCEPTED, key);
			}else if(status.equals(acme.entities.patronages.Status.PROPOSED.toString())){
				averageBudgetByStatus.put(acme.entities.patronages.Status.PROPOSED, key);
			}else {
				averageBudgetByStatus.put(acme.entities.patronages.Status.DENIED, key);
			}
			i++;
		 }
		i=0;
		tamaño = this.repository.deviationBudgetPatronages().size();
		while(i<tamaño) {
			final String linea= this.repository.deviationBudgetPatronages().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String status=sub[2];
			if(status.equals(acme.entities.patronages.Status.ACCEPTED.toString())) {
				deviationBudgetByStatus.put(acme.entities.patronages.Status.ACCEPTED, key);
			}else if(status.equals(acme.entities.patronages.Status.PROPOSED.toString())){
				deviationBudgetByStatus.put(acme.entities.patronages.Status.PROPOSED, key);
			}else {
				deviationBudgetByStatus.put(acme.entities.patronages.Status.DENIED, key);
			}
			
			i++;
		 }
		i=0;
		tamaño = this.repository.minimumBudgetPatronages().size();
		while(i<tamaño) {
			final String linea= this.repository.minimumBudgetPatronages().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String status=sub[2];
			if(status.equals(acme.entities.patronages.Status.ACCEPTED.toString())) {
				minBudgetByStatus.put(acme.entities.patronages.Status.ACCEPTED, key);
			}else if(status.equals(acme.entities.patronages.Status.PROPOSED.toString())){
				minBudgetByStatus.put(acme.entities.patronages.Status.PROPOSED, key);
			}else {
				minBudgetByStatus.put(acme.entities.patronages.Status.DENIED, key);
			}
			i++;
		 }
		i=0;
		tamaño = this.repository.maximumBudgetPatronages().size();
		while(i<tamaño) {
			final String linea= this.repository.maximumBudgetPatronages().get(i);
			final String[] sub=linea.split(",");
			final Double key=Double.parseDouble(sub[1]);
			final String status=sub[2];
			if(status.equals(acme.entities.patronages.Status.ACCEPTED.toString())) {
				maxBudgetByStatus.put(acme.entities.patronages.Status.ACCEPTED, key);
			}else if(status.equals(acme.entities.patronages.Status.PROPOSED.toString())){
				maxBudgetByStatus.put(acme.entities.patronages.Status.PROPOSED, key);
			}else {
				maxBudgetByStatus.put(acme.entities.patronages.Status.DENIED, key);
			}
			i++;
		 }		
		
		
		result = new AdminDashboard();
		
		result.setTotalNumberOftools(totalNumberOfTools);
		result.setAverageOfToolsRetailPrice(averageRetailPriceOfTools);
		result.setDeviationOfToolsRetailPrice(deviationRetailPriceOfTools);
		result.setMinimumOfToolsRetailPrice(minRetailPriceOfTools);
		result.setMaximumOfToolsRetailPrice(maxRetailPriceOfTools);
		
		
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setAverageOfComponentsRetailPrice(averageOfComponentsRetailPrice);
		result.setDeviationOfComponentsRetailPrice(deviationOfComponentsRetailPrice);
		result.setMaximumOfComponentsRetailPrice(maximumOfComponentsRetailPrice);
		result.setMinimumOfComponentsRetailPrice(minimumOfComponentsRetailPrice);
		
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setAverageBudgetPatronages(averageBudgetByStatus);
		result.setDeviationBudgetPatronages(deviationBudgetByStatus);
		result.setMinimumBudgetPatronages(maxBudgetByStatus);
		result.setMaximumBudgetPatronages(minBudgetByStatus);

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
