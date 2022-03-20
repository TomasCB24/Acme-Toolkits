package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfComponentsByTechnologyAndCurrency;
	Money						averageOfComponentsRetailPriceByTechnologyAndCurrency;
	Money						deviationOfComponentsRetailPriceByTechnologyAndCurrency;
	Money						minimumOfComponentsRetailPriceByTechnologyAndCurrency;
	Money						maximumOfComponentsRetailPriceByTechnologyAndCurrency;
	
	Integer						totalNumberOftoolsByCurrency;
	Money						averageOfToolsRetailPriceByCurrency;
	Money						deviationOfToolsRetailPriceByCurrency;
	Money						minimumOfToolsRetailPriceByCurrency;
	Money						maximumOfToolsRetailPriceByCurrency;
	
	Integer						totalNumberOfProposedPatronages;
	Integer						totalNumberOfAcceptedPatronages;
	Integer						totalNumberOfDeniedPatronages;
	
	Money						averageBudgetOfProposedPatronages;
	Money						averageBudgetOfAcceptedPatronages;
	Money						averageBudgetOfDeniedPatronages;
	
	Money						deviationBudgetOfProposedPatronages;
	Money						deviationBudgetOfAcceptedPatronages;
	Money						deviationBudgetOfDeniedPatronages;
	
	Money						minimumBudgetOfProposedPatronages;
	Money						minimumBudgetOfAcceptedPatronages;
	Money						minimumBudgetOfDeniedPatronages;
	
	Money						maximumBudgetOfProposedPatronages;
	Money						maximumBudgetOfAcceptedPatronages;
	Money						maximumBudgetOfDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
