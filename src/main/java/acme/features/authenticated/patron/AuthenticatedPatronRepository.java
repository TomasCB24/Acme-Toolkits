package acme.features.authenticated.patron;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface AuthenticatedPatronRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select c from Patron c where c.userAccount.id = :id")
	Patron findOnePatronByUserAccountId(int id);

}
