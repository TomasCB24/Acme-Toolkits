package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.id = :itemId")
	Item findOneItemById(int itemId);
	
	@Query("select q.item from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findManyItemsByMasterId(int masterId);
	
	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);

}
