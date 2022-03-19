package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	int							totalNumberOfProposedPatronages;
	Map<String, Double>			averageBudgetOfProposedPatronages;
	Map<String, Double>			deviationBudgetOfProposedPatronages;
	Map<String, Double>			minimumBudgetOfProposedPatronages;
	Map<String, Double>			maximumBudgetOfProposedPatronages;
	
	int							totalNumberOfAcceptedPatronages;
	Map<String, Double>			averageBudgetOfAcceptedPatronages;
	Map<String, Double>			deviationBudgetOfAcceptedPatronages;
	Map<String, Double>			minimumBudgetOfAcceptedPatronages;
	Map<String, Double>			maximumBudgetOfAcceptedPatronages;
	
	int							totalNumberOfDeniedPatronages;
	Map<String, Double>			averageBudgetOfDeniedPatronages;
	Map<String, Double>			deviationBudgetOfDeniedPatronages;
	Map<String, Double>			minimumBudgetOfDeniedPatronages;
	Map<String, Double>			maximumBudgetOfDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
