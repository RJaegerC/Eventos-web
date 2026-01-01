package architeture.hexagonal.adapters.outbound.repositories;

import architeture.hexagonal.adapters.outbound.entities.JpaEventEntity;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventRepository;
import architeture.hexagonal.models.event.EventRequestDTO;
import architeture.hexagonal.utils.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class EventRepositoryImp implements EventRepository {

    private final JpaEventRepository jpaEventRepository;

    @Autowired
    private EventMapper mapper;

    public EventRepositoryImp(JpaEventRepository jpaEventRepository) {
        this.jpaEventRepository = jpaEventRepository;
        this.eventMapper = new EventMapper() {
            @Override
            public Event toEntity(EventRequestDTO dto, String imgUrl) {
                return null;
            }

            @Override
            public EventRequestDTO toDto(Event entity) {
                return null;
            }
        }
    }

    @Override
    public Event save(Event event) {
        JpaEventEntity eventEntity = new JpaEventEntity(event);
        this.jpaEventRepository.save(eventEntity);
        return eventEntity.map(mapper::jpaToDomain);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        Optional<JpaEventEntity> eventEntity = this.jpaEventRepository.findById(id);
        return eventEntity.map(entity -> new Event(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getImgUrl(), entity.getEventUrl(), entity.getRemote(), entity.getDate()).orElse(null));
    }

    @Override
    public List<Event> findAll() {
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
    public Page<EventAdressProjection> findUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page size);
        return this.jpaEventRepository.findUpcomingEvents(new Date(), pageable);
    }

    @Override
    public Page<EventAddressProjection> findFilteredEvents(String city, String uf, Date startDate, Date endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page size);
        return this.jpaEventRepository.findFilteredEvents(city, uf, startDate, endDate, pageable);
    }

    @Override
    public List<EventAdressProjection> findEventsByTitle(String title) {
        return this.jpaEventRepository.findEventsByTitle(title);
    }
}
