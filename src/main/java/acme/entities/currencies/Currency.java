package acme.entities.currencies;

import javax.persistence.Entity;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Currency extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected String			currencyType="EUR";

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
