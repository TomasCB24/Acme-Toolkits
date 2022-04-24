package acme.forms;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyExchange {
	
	// Query attributes -------------------------------------------------------

	@NotNull
	@Valid
	public Money	source;

	@NotBlank
	public String	targetCurrency;

	// Response attributes ----------------------------------------------------

	@Valid
	public Money	target;

	public Date		date;

}
