package architeture.hexagonal.application.usecases;

import architeture.hexagonal.models.event.Event;

import java.util.UUID;

public interface EventUseCases {

    public Event createEvent(EventRequestDTO data);

    public List<EventResponseDTO> getUpcomingEvents(int page, int size);

    public EventDetailsDTO getEventDetails(UUID id);

    public void deleteEvent(UUID id, String adminKey);

    public List<EventResponseDTO> searchEvents(String title);

    public List<EventResponseDTO> getFilteredEvents();

}
