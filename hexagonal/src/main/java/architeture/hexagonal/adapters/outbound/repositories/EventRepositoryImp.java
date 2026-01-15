package architeture.hexagonal.adapters.outbound.repositories;

import architeture.hexagonal.adapters.outbound.entities.JpaEventEntity;
import architeture.hexagonal.models.event.*;
import architeture.hexagonal.utils.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class EventRepositoryImp implements EventRepository {

    private final JpaEventRepository jpaEventRepository;
    private final EventMapper mapper;

    @Autowired
    public EventRepositoryImp(JpaEventRepository jpaEventRepository, EventMapper mapper) {
        this.jpaEventRepository = jpaEventRepository;
        this.mapper = mapper;
    }

    @Override
    public Event save(Event event) {
        JpaEventEntity savedEntity = this.jpaEventRepository.save(new JpaEventEntity(event));
        return mapper.jpaToDomain(savedEntity);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return this.jpaEventRepository.findById(id)
                .map(mapper::jpaToDomain);
    }

    @Override
    public List<Event> findAll() {
        return this.jpaEventRepository.findAll()
                .stream()
                .map(mapper::jpaToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        this.jpaEventRepository.deleteById(id);
    }

    @Override
    public Page<EventAddressProjection> findUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.jpaEventRepository.findUpcomingEvents(new Date(), pageable);
    }

    @Override
    public Page<EventAddressProjection> findFilteredEvents(String city, String uf, Date startDate, Date endDate, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.jpaEventRepository.findFilteredEvents(city, uf, startDate, endDate, pageable);
    }

    @Override
    public List<EventAddressProjection> findEventsByTitle(String title) {
        return this.jpaEventRepository.findEventsByTitle(title);
    }
}
