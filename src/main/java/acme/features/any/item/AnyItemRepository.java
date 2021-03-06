package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :itemId")
	Item findOneItemById(int itemId);
	
	@Query("select q.item from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findManyItemsByMasterId(int masterId);
	
	@Query("select q.toolkit from Quantity q where q.item.id = :itemId and q.toolkit.draftMode = false")
	Collection<Toolkit> findManyPublishedToolkitsByItemId(int itemId);
	
	@Query("select q.number from Quantity q where q.toolkit.id = :toolkitId and q.item.id = :itemId")
	Integer findQuantityForItemInToolkit(int toolkitId, int itemId);
	
	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);
  
	@Query("select i from Item i where i.draftMode = false")
	Collection<Item> findItemsPublished();
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();


}
