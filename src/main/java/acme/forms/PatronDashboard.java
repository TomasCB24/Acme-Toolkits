package acme.forms;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfProposedPatronages;
	Integer						totalNumberOfAcceptedPatronages;
	Integer						totalNumberOfDeniedPatronages;
	
	Money						averageBudgetOfProposedPatronages;
	Money						deviationBudgetOfProposedPatronages;
	Money						minimumBudgetOfProposedPatronages;
	Money						maximumBudgetOfProposedPatronages;
	
	Money						averageBudgetOfAcceptedPatronages;
	Money						deviationBudgetOfAcceptedPatronages;
	Money						minimumBudgetOfAcceptedPatronages;
	Money						maximumBudgetOfAcceptedPatronages;
	
	Money						averageBudgetOfDeniedPatronages;
	Money						deviationBudgetOfDeniedPatronages;
	Money						minimumBudgetOfDeniedPatronages;
	Money						maximumBudgetOfDeniedPatronages;


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
