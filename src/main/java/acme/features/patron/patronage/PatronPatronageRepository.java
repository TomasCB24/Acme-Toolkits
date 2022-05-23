package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {
	
	@Query("select p from Patronage p where p.patron.id = :patronId")
	Collection<Patronage> findManyPatronagesByPatronId(int patronId);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findOnePatronageById(int id);
	
	@Query("select p from Patronage p where p.code = :code")
	Patronage findOnePatronageByCode(String code);
	
	@Query("select p from Patron p where p.id = :id")
	Patron findOnePatronById(int id);
	
	@Query("select p from PatronageReport p where p.patronage.id = :patronageId")
	Collection<PatronageReport> findManyPatronageReportsByPatronageId(int patronageId);
	
	@Query("select i from Inventor i where i.userAccount.username = :username")
	Inventor findOneInventorByUserName(String username);
}
