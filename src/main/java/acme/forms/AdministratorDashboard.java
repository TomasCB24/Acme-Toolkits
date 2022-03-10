package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------

	Double						totalNumberOfComponents;
	Double						averageOfComponentsRetailPrice;
	Double						deviationOfComponentsRetailPrice;
	Double						minimumOfComponentsRetailPrice;
	Double						maximumOfComponentsRetailPrice;
	
	Double						totalNumberOftools;
	Double						averageOfToolsRetailPrice;
	Double						deviationOfToolsRetailPrice;
	Double						minimumOfToolsRetailPrice;
	Double						maximumOfToolsRetailPrice;
	
	Double						totalNumberOfProposedPatronages;
	Double						totalNumberOfAcceptedPatronages;
	Double						totalNumberOfDeniedPatronages;
	
	Double						averageOfProposedPatronages;
	Double						averageOfAcceptedPatronages;
	Double						averageOfDeniedPatronages;
	
	Double						deviationOfProposedPatronages;
	Double						deviationOfAcceptedPatronages;
	Double						deviationOfDeniedPatronages;
	
	Double						minimumOfProposedPatronages;
	Double						minimumOfAcceptedPatronages;
	Double						minimumOfDeniedPatronages;
	
	Double						maximumOfProposedPatronages;
	Double						maximumOfAcceptedPatronages;
	Double						maximumOfDeniedPatronages;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
