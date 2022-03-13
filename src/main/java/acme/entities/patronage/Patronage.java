package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
public class Patronage extends AbstractEntity{
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected Status status;
	
	@Column(unique=true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotNull
	protected String code;
	
	@NotBlank
	@Length(max=256)
	protected String legalStuff;
	
	@NotNull
	protected Money budget;
	
	@Past
	@Temporal(TemporalType.DATE)
	@NotNull
	protected Date	creationDate;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	protected Date	initialPeriodDate;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	protected Date	finalPeriodDate;
	
	@URL
	protected String link;
	
	// Relationships ----------------------------------------------------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Patron patron;
	
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Inventor inventor;


}