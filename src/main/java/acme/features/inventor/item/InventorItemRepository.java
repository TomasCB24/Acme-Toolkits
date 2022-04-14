package acme.features.inventor.item;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository {
	
	@Query("select i from Item i where i.id = :itemId")
	Item findOneItemById(int itemId);
	
	@Query("select i from Item i where i.inventor.id = :inventorId")
	Collection<Item> findManyItemsByInventorId(int inventorId);
	

}