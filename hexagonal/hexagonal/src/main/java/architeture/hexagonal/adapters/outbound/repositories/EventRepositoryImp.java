package architeture.hexagonal.adapters.outbound.repositories;

import architeture.hexagonal.adapters.outbound.entities.JpaEventEntity;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class EventRepositoryImp implements EventRepository {

    private final JpaEventRepository jpaEventRepository;

    public EventRepositoryImp(JpaEventRepository jpaEventRepository) {
        this.jpaEventRepository = jpaEventRepository;
    }

    @Override
    public Event save(Event event) {
        JpaEventEntity eventEntity = new JpaEventEntity(event);
        this.jpaEventRepository.save(eventEntity);
        return new Event(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getImgUrl(), entity.getEventUrl(), entity.getRemote(), entity.getDate());
    }

    @Override
    public Event findById(UUID id) {
        Optional<JpaEventEntity> eventEntity = this.jpaEventRepository.findById(id);
        return eventEntity.map(entity -> new Event(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getImgUrl(), entity.getEventUrl(), entity.getRemote(), entity.getDate()).orElse(null));
    }

    @Override
    public List <Event> findAll() {
        return this.jpaEventRepository.findALL()
                .stream()
                .map(entity -> new Event(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getImgUrl(), entity.getEventUrl(), entity.getRemote(), entity.getDate())
                        .collect(Collectors.toList()));

    }

    @Override
    public void deletById(UUID id) {
        this.jpaEventRepository.deleteById(id);
    }

    @Override
    public Page<EventAdressProjection> findUpcomingEvents() {
        return null;
    }

    @Override
    public Page<EventAddressProjection> findFilteredEvents(String city, String uf, String startDate, String endDate) {
        return null;
    }

    @Override
    public List<EventAdressProjection> findEventsByTitle(String title) {
        return List.of();
    }
}
