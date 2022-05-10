package acme.testing.authenticated.announcement;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcements.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnnouncementRepository extends AbstractRepository{
	
	@Query("select a from Announcement a where a.creationMoment between '2022/05/01' and '2022/05/31'")
	Collection<Announcement> findAnnouncementsToPatch();

}
