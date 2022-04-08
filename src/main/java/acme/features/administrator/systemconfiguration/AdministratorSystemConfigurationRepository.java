package acme.features.administrator.systemconfiguration;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSystemConfigurationRepository extends AbstractRepository{

	@Query("select sc from SystemConfiguration sc")
	Collection<SystemConfiguration> findSystemConfiguration();
	
}
