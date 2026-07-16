package architeture.hexagonal.adapters.inbound.services;

import architeture.hexagonal.application.usecases.AddressUseCases;
import architeture.hexagonal.models.adress.Address;
import architeture.hexagonal.models.adress.AddressRepository;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressUseCases {

    private final AddressRepository addressRepository;

    @Override
    public void createAddress(EventRequestDTO data, Event event) {
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEventId(event.getId());
    }

    @Override
    public Optional<Address> findByEventId(UUID eventId) {
        return addressRepository.findByEventId(eventId);
    }
}
