package acme.entities.patronages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatronageReport extends AbstractEntity {
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	
	@Column(unique=true)
	@Max(9999)
	@Min(1)
	@NotNull
	protected Integer serialNumber;
	
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date	creationMoment;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String memorandum;
	
	@URL
	protected String link;
	
	// Derived attributes -----------------------------------------------------
	public String sequenceNumber() {
		String serialNumberToString;
		if(this.serialNumber>999) {
			serialNumberToString = this.serialNumber.toString();
		}else if(this.serialNumber>99) {
			serialNumberToString="0"+this.serialNumber.toString();
		}else if(this.serialNumber>9){
			serialNumberToString="00"+this.serialNumber.toString();
		}else {
			serialNumberToString="000"+this.serialNumber.toString();
		}
		return this.patronage.getCode() + ":" + serialNumberToString;
	}
	
	// Relationships ----------------------------------------------------------


	@NotNull
	@Valid
	@ManyToOne
	protected Patronage patronage;
	
}