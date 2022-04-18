package acme.features.any.item;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.id = :itemId")
	Item findOneItemById(int itemId);
	

	@Query("select q.item from Quantity q where q.toolkit.draftMode = 0")
	Collection<Item> findItemsPublished();
	

}
