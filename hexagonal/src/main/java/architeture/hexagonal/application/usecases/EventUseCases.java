package architeture.hexagonal.application.usecases;

import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventDetailsDTO;
import architeture.hexagonal.models.event.EventRequestDTO;
import architeture.hexagonal.models.event.EventResponseDTO;

import java.util.List;
import java.util.UUID;

public interface EventUseCases {

    public Event createEvent(EventRequestDTO data);

    public List<EventResponseDTO> getUpcomingEvents(int page, int size);

    public EventDetailsDTO getEventDetails(UUID id);

    public void deleteEvent(UUID id, String adminKey);

    public List<EventResponseDTO> searchEvents(String title);

    public List<EventResponseDTO> getFilteredEvents();

}
