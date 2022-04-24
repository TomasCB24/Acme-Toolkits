package acme.entities.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity{
	
	private static final String SPAM_WORDS_PATTERN = "^[a-zñáéíóú\\\\s']+(,[a-zñáéíóú\\\\s']+)*$";
	
	
	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String				systemCurrency;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}+(,[A-Z]{3}+)*$")
	protected String				acceptedCurrencies;
	
	@NotBlank
	@Pattern(regexp = SystemConfiguration.SPAM_WORDS_PATTERN)
	protected String				strongSpamWords;
	
	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 2)
	protected double				strongSpamThreshold;
	
	@NotBlank
	@Pattern(regexp = SystemConfiguration.SPAM_WORDS_PATTERN)
	protected String				weakSpamWords;

	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 2)
	protected double				weakSpamThreshold;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
}
