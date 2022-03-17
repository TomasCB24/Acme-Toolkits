package acme.entities.items;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.entities.toolkits.Toolkit;
import acme.framework.entities.AbstractEntity;

public class Quantity extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Min(1)
	protected Integer			number;
	
	

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@NotNull
    @Valid
    @ManyToOne(optional = false)
    protected Item item;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Toolkit toolkit;

}
