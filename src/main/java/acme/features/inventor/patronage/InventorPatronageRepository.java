package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository  extends AbstractRepository {

	@Query("select p from Patronage p where p.inventor.id = :inventorId")
	Collection<Patronage> findManyPatronagesByInventorId(int inventorId);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);
}

