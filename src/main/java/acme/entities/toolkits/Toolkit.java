package acme.entities.toolkits;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Column(unique=true)
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String			code;

	@NotBlank
	@Length(max = 101)
	protected String			title;

	@NotBlank
	@Length(max = 256)
	protected String			description;

	@NotBlank
	@Length(max = 256)
	protected String			assemblyNotes;

	@URL
	protected String 			link;

	@NotBlank
	@Length(max = 101)
	protected String			toolName;
	
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String			toolCode;

	@NotBlank
	@Length(max = 101)
	protected String			toolTechnology;
	
	@NotBlank
	@Length(max = 256)
	protected String			toolDescription;

	@NotNull
	@Valid
	protected Money				toolRetailPrice;
	
	@URL
	protected String 			toolLink;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
}
