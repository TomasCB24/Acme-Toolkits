package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {
	@Query("select p from PatronageReport p where p.id= :patronageReportId")
	PatronageReport findOnePatronageReportById(int patronageReportId);
	
	@Query("select p from PatronageReport p where p.patronage.patron.userAccount.id = :patronId")
	Collection<PatronageReport> findManyByPatronId(int patronId);

}
