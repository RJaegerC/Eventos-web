package architeture.hexagonal.adapters.inbound.services;

import architeture.hexagonal.application.services.AddressService;
import architeture.hexagonal.application.usecases.AddressUseCases;
import architeture.hexagonal.models.adress.Address;
import architeture.hexagonal.models.adress.AddressRepository;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventRequestDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImp implements AddressUseCases {

    private final AddressUseCases useCase;

    public AddressServiceImp(AddressRepository addressRepository) {
        this.useCase = new AddressService(addressRepository);
    }

    @Override
    public void createAddress(EventRequestDTO data, Event event) {
        useCase.createAddress(data, event);
    }

    @Override
    public Optional<Address> findByEventId(UUID eventId) {
        return useCase.findByEventId(eventId);
    }
}
