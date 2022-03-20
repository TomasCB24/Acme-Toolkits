package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	int							totalNumberOfProposedPatronages;
	int							totalNumberOfAcceptedPatronages;
	int							totalNumberOfDeniedPatronages;
	Map<Pair<String,String>, Double>			averageBudgetOfPatronages;
	Map<Pair<String,String>, Double>			deviationBudgetOfPatronages;
	Map<Pair<String,String>, Double>			minimumBudgetOfPatronages;
	Map<Pair<String,String>, Double>			maximumBudgetOfPatronages;


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
