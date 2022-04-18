/*
 * AdministratorDashboardRepository.java
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

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	
	@Query("select count(c) from Item c where c.type = acme.entities.items.ItemType.COMPONENT")
	int totalNumberOfComponents();	
	
	@Query("select count(c) from Item c where c.type = acme.entities.items.ItemType.TOOL")
	int totalNumberOfTools();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.patronages.Status.PROPOSED")
	int totalNumberOfProposedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.patronages.Status.ACCEPTED")
	int totalNumberOfAcceptedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = acme.entities.patronages.Status.DENIED")
	int totalNumberOfDeniedPatronages();
	
	//Component
	
	@Query("select i.retailPrice.currency, i.technology, avg(i), i.type from Item i where i.type=acme.entities.items.ItemType.COMPONENT group by i.retailPrice.currency, i.technology")
	List<String> averageOfComponentsRetailPrice();
	
	@Query("select i.retailPrice.currency, i.technology, stddev(i), i.type from Item i where i.type=acme.entities.items.ItemType.COMPONENT group by i.retailPrice.currency, i.technology")
	List<String> deviationOfComponentsRetailPrice();
	
	@Query("select i.retailPrice.currency, i.technology, min(i.retailPrice.amount), i.type from Item i where i.type=acme.entities.items.ItemType.COMPONENT group by i.retailPrice.currency, i.technology")
	List<String> minimumOfComponentsRetailPrice();
	
	@Query("select i.retailPrice.currency, i.technology, max(i.retailPrice.amount), i.type from Item i where i.type=acme.entities.items.ItemType.COMPONENT group by i.retailPrice.currency, i.technology")
	List<String> maximumOfComponentsRetailPrice();
	
	//Tool
	
	@Query("select i.retailPrice.currency, avg(i), i.type from Item i where i.type=acme.entities.items.ItemType.TOOL group by i.retailPrice.currency")
	List<String> averageOfToolsRetailPrice();
	
	@Query("select i.retailPrice.currency, stddev(i), i.type from Item i where i.type=acme.entities.items.ItemType.TOOL group by i.retailPrice.currency")
	List<String> deviationOfToolsRetailPrice();
	
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount), i.type from Item i where i.type=acme.entities.items.ItemType.TOOL group by i.retailPrice.currency")
	List<String> minimumOfToolsRetailPrice();
	
	@Query("select i.retailPrice.currency, min(i.retailPrice.amount), i.type from Item i where i.type=acme.entities.items.ItemType.TOOL group by i.retailPrice.currency")
	List<String> maximumOfToolsRetailPrice();
	
	//Patronages
	
	@Query("select p.budget.currency, avg(p.budget.amount), p.status from Patronage p group by p.budget.currency, p.status")
	List<String> averageBudgetPatronages();
	
	@Query("select p.budget.currency, stddev(p.budget.amount), p.status from Patronage p group by p.budget.currency, p.status")
	List<String> deviationBudgetPatronages();
	
	@Query("select p.budget.currency, min(p.budget.amount), p.status from Patronage p group by p.budget.currency, p.status")
	List<String> minimumBudgetPatronages();
	
	@Query("select p.budget.currency, max(p.budget.amount), p.status from Patronage p group by p.budget.currency, p.status")
	List<String> maximumBudgetPatronages();
	
	

}
