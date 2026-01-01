package architeture.hexagonal.models.event;

import java.util.List;
import java.util.UUID;

public interface EventRepository {

    Event save(Event event);

    Event findById(UUID id);

    List<Event> findAll();

    void deletById(UUID id);

    Page<EventAdressProjection> findUpcomingEvents();

    Page<EventAddressProjection> findFilteredEvents(String city, String uf, String startDate, String endDate);

    List<EventAdressProjection> findEventsByTitle(String title);

}
