package acme.entities.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	protected String				acceptedCurrencies;
	
	@NotBlank
	protected String				systemCurrency;
	
	@NotBlank
	protected String				strongSpamWords;
	
	@Range(min = 0, max = 100)
	@Digits(integer = 2, fraction = 2)
	protected double				strongSpamThreshold;
	
	@NotBlank
	protected String				weakSpamWords;

	@Range(min = 0, max = 100)
	@Digits(integer = 2, fraction = 2)
	protected double				weakSpamThreshold;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
}
