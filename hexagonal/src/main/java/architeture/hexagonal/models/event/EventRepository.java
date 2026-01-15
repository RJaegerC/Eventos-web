package architeture.hexagonal.models.event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {

    Event save(Event event);

    Optional<Event> findById(UUID id);

    List<Event> findAll();

    void deleteById(UUID id);

    Page<EventAdressProjection> findUpcomingEvents(int page, int size);

    Page<EventAddressProjection> findFilteredEvents(String city, String uf, Date startDate, Date endDate, int page, int size);

    List<EventAdressProjection> findEventsByTitle(String title);

}
