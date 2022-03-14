package acme.entities.spams;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected SpamType				spamType;
	
	@NotNull
	protected Language			 	language;
	
	@NotBlank
	protected String				word;

	// Derived attributes -----------------------------------------------------

	public Double threshold() {

		if(this.spamType.equals(SpamType.STRONG)) {
			return 0.1;
		}else {
			return 0.25;
		}
	}
	
	// Relationships ----------------------------------------------------------
	
}
