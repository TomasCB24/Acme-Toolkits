package acme.testing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.announcements.Announcement;
import acme.entities.chirps.Chirp;
import acme.framework.helpers.FactoryHelper;
import acme.testing.any.chirp.ChirpRepository;
import acme.testing.authenticated.announcement.AnnouncementRepository;

public class TemporalAwareTestHarness extends TestHarness{

	// Lifecycle management ---------------------------------------------------

	@Autowired
	private AnnouncementRepository announcementRepository;
	
	@Autowired
	private ChirpRepository chirpRepository;
	
	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
		FactoryHelper.autowire(this);
		this.patchAnnouncements();
		this.patchChirps();
	}

	// Business methods -------------------------------------------------------


	protected void patchAnnouncements() {
		Collection<Announcement> announcements;
		Date moment;

		announcements = this.announcementRepository.findAnnouncementsToPatch();
		for (final Announcement announcement : announcements) {
			moment = this.adjustMoment(announcement.getCreationMoment());
			announcement.setCreationMoment(moment);
			this.announcementRepository.save(announcement);
		}
	}
	
	protected void patchChirps() {
		Collection<Chirp> chirps;
		Date moment;

		chirps = this.chirpRepository.findChirpsToPatch();
		for (final Chirp chirp : chirps) {
			moment = this.adjustMoment(chirp.getCreationMoment());
			chirp.setCreationMoment(moment);
			this.chirpRepository.save(chirp);
		}
	}

	protected String computeDeltaMoment(final int deltaDays) {
		assert deltaDays <= 0;

		String result;
		Calendar calendar;
		SimpleDateFormat formatter;

		calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		calendar.add(Calendar.DAY_OF_MONTH, deltaDays);
		formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		result = formatter.format(calendar.getTime());

		return result;
	}

	protected Date adjustMoment(final Date currentMoment) {
		assert currentMoment != null;

		Date result;
		Calendar calendar;
		int day;

		calendar = Calendar.getInstance();

		calendar.setTime(currentMoment);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		calendar.add(Calendar.DAY_OF_MONTH, -day);

		result = calendar.getTime();

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	
}
