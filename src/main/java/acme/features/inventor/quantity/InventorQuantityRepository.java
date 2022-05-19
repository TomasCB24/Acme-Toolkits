package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.items.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorQuantityRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.code = :code")
	Item findOneItemByCode(String code);
	
	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
	
	@Query("select q from Quantity q where q.toolkit.id = :toolkitId")
	Collection<Quantity> findManyQuantitiesByToolkitId(int toolkitId);
	
	@Query("select q.toolkit from Quantity q where q.id=:quantityId")
	Toolkit findOneToolkitByQuantityId(int quantityId);
	
	@Query("select q from Quantity q where q.id = :quantityId")
	Quantity findOneQuantityById(int quantityId);
	
	@Query("select q from Quantity q where q.item.code = :itemCode and q.toolkit.id = :toolkitId")
	Quantity findOneQuantityByItemId(int toolkitId, String itemCode);
	
	@Query("select i from Item i where i.draftMode = false")
	Collection<Item> findManyPublishedItems();
	
}
