package acme.forms;

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
	
	Double						averageBudgetOfProposedPatronages;
	Double						deviationBudgetOfProposedPatronages;
	Double						minimumBudgetOfProposedPatronages;
	Double						maximumBudgetOfProposedPatronages;
	
	Double						averageBudgetOfAcceptedPatronages;
	Double						deviationBudgetOfAcceptedPatronages;
	Double						minimumBudgetOfAcceptedPatronages;
	Double						maximumBudgetOfAcceptedPatronages;
	
	Double						averageBudgetOfDeniedPatronages;
	Double						deviationBudgetOfDeniedPatronages;
	Double						minimumBudgetOfDeniedPatronages;
	Double						maximumBudgetOfDeniedPatronages;


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
