package acme.entities.exchange;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MoneyExchangeCache extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String				sourceCurrency;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String				targetCurrency;
	
	protected double				rate;
	
	@Temporal(TemporalType.DATE)
    @NotNull
    protected Date    lastUpdate;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
}
