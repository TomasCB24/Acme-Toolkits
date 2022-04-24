/*
 * AnyToolkitRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AnyToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
	
	@Query("select t from Toolkit t where t.draftMode = false")
	Collection<Toolkit> findManyToolkitsPublished();
	
	@Query("select sum(q.item.retailPrice.amount*q.number) from Quantity q where q.toolkit.id = :toolkitId")
	Double computeRetailPriceByToolkitId(int toolkitId);
	
	@Query("Select q.item.retailPrice from Quantity q where q.toolkit.id = :toolkitId")
	Collection<Money> getRetailPricesByToolkitId(int toolkitId);
	
	@Query("Select q.item from Quantity q where q.toolkit.id = :toolkitId")
	Collection<Item> findItemsByToolkitId(int toolkitId);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

}
