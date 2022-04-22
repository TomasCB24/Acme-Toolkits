package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronages.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------

	int								totalNumberOfComponents;
	//The strings are Technology and Currency and the double the value of this Technology and Currency pair for average or other stat
	Map<Pair<String,String>,Double> averageOfComponentsRetailPrice; 
	Map<Pair<String,String>,Double> deviationOfComponentsRetailPrice;
	Map<Pair<String,String>,Double> minimumOfComponentsRetailPrice;
	Map<Pair<String,String>,Double> maximumOfComponentsRetailPrice;
		
	int								totalNumberOftools;	
	//The strings are the currency and the double the value of this currency for average or other stat
	Map<String,Double>				averageOfToolsRetailPrice;
	Map<String,Double>				deviationOfToolsRetailPrice;
	Map<String,Double>				minimumOfToolsRetailPrice;
	Map<String,Double>				maximumOfToolsRetailPrice;
	
	
	int								totalNumberOfProposedPatronages;
	int								totalNumberOfAcceptedPatronages;
	int								totalNumberOfDeniedPatronages;
	
	//The strings are "proposed", "accepted" or "denied"
	Map<Pair<String,Status>, Double>				averageBudgetPatronages;
	Map<Pair<String,Status>, Double> 				deviationBudgetPatronages;
	Map<Pair<String,Status>, Double> 				minimumBudgetPatronages;
	Map<Pair<String,Status>, Double> 				maximumBudgetPatronages;
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
