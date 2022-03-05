package acme.entities.chirp;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chirp extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected LocalDate			creationMoment = LocalDate.now();

	@NotBlank
	@Length(max = 101)
	protected String			title;
	
	@NotBlank
	@Length(max = 101)
	protected String			author;
	
	@NotBlank
	@Length(max = 256)
	protected String			body;
	
	@Email
	protected String			email;


}
