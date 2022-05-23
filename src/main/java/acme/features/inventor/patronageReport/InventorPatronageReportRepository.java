package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {
	@Query("select p from PatronageReport p where p.id= :patronageReportId")
	PatronageReport findOnePatronageReportById(int patronageReportId);
	
	@Query("select p from PatronageReport p where p.patronage.inventor.userAccount.id = :inventorId")
	Collection<PatronageReport> findManyByInventorId(int inventorId);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findInventorById(int id);

	@Query("select pr from PatronageReport pr where pr.serialNumber = :serialNumber")
	PatronageReport findOnePatronageReportBySerialNumber(int serialNumber);
	
	@Query("select p from Patronage p where p.code = :code")
	Patronage findOnePatronageByCode(String code);

}
