package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{
	
	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
	
	@Query("select t from Toolkit t where t.inventor.id = :inventorId")
	Collection<Toolkit> findManyToolkitsByInventorId(int inventorId);
	
	@Query("select sum(q.item.retailPrice.amount) from Quantity q where q.toolkit.id = :toolkitId")
	Double computeRetailPriceByToolkitId(int toolkitId);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	Collection<String> findSystemCurrency();
	
}
