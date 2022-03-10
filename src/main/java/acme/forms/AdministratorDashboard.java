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

	Double						totalNumberOfComponents;
	Money						averageOfComponentsRetailPrice;
	Money						deviationOfComponentsRetailPrice;
	Money						minimumOfComponentsRetailPrice;
	Money						maximumOfComponentsRetailPrice;
	
	Double						totalNumberOftools;
	Money						averageOfToolsRetailPrice;
	Money						deviationOfToolsRetailPrice;
	Money						minimumOfToolsRetailPrice;
	Money						maximumOfToolsRetailPrice;
	
	Double						totalNumberOfProposedPatronages;
	Double						totalNumberOfAcceptedPatronages;
	Double						totalNumberOfDeniedPatronages;
	
	Double						averageOfProposedPatronages;
	Double						averageOfAcceptedPatronages;
	Double						averageOfDeniedPatronages;
	
	Double						deviationOfProposedPatronages;
	Double						deviationOfAcceptedPatronages;
	Double						deviationOfDeniedPatronages;
	
	Money						minimumOfProposedPatronages;
	Money						minimumOfAcceptedPatronages;
	Money						minimumOfDeniedPatronages;
	
	Money						maximumOfProposedPatronages;
	Money						maximumOfAcceptedPatronages;
	Money						maximumOfDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
