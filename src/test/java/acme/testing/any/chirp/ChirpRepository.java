package acme.testing.any.chirp;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chirps.Chirp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChirpRepository extends AbstractRepository{
	
	@Query("select c from Chirp c where c.creationMoment between '2022/05/01' and '2022/05/31'")
	Collection<Chirp> findChirpsToPatch();

}
