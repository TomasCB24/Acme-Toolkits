/*
 * UserIdentity.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.datatypes;

import javax.persistence.Embeddable;

import acme.framework.entities.DefaultUserIdentity;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class UserIdentity extends DefaultUserIdentity {

	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;

}
