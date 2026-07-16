package architeture.hexagonal.application.usecases;

import architeture.hexagonal.models.adress.Address;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventRequestDTO;

import java.util.Optional;
import java.util.UUID;

public interface AddressUseCases {

    void createAddress(EventRequestDTO data, Event event);

    Optional<Address> findByEventId(UUID eventId);

}
