package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {
	@Query("select p from PatronageReport p where p.id= :patronageReportId")
	PatronageReport findOnePatronageReportById(int patronageReportId);
	
	@Query("select p from PatronageReport p")
	Collection<PatronageReport> findManyByInventorId(int inventorId);
	

}
