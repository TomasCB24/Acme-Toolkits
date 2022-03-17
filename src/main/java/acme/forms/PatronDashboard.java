package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfProposedPatronages;
	Integer						totalNumberOfAcceptedPatronages;
	Integer						totalNumberOfDeniedPatronages;
	
	Money						averageBudgetOfProposedPatronagesGroupedByCurrency;
	Money						deviationBudgetOfProposedPatronagesGroupedByCurrency;
	Money						minimumBudgetOfProposedPatronagesGroupedByCurrency;
	Money						maximumBudgetOfProposedPatronagesGroupedByCurrency;
	
	Money						averageBudgetOfAcceptedPatronagesGroupedByCurrency;
	Money						deviationBudgetOfAcceptedPatronagesGroupedByCurrency;
	Money						minimumBudgetOfAcceptedPatronagesGroupedByCurrency;
	Money						maximumBudgetOfAcceptedPatronagesGroupedByCurrency;
	
	Money						averageBudgetOfDeniedPatronagesGroupedByCurrency;
	Money						deviationBudgetOfDeniedPatronagesGroupedByCurrency;
	Money						minimumBudgetOfDeniedPatronagesGroupedByCurrency;
	Money						maximumBudgetOfDeniedPatronagesGroupedByCurrency;


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
