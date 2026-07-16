package architeture.hexagonal.application.services;

import architeture.hexagonal.application.usecases.AddressUseCases;
import architeture.hexagonal.models.adress.Address;
import architeture.hexagonal.models.adress.AddressRepository;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventRequestDTO;

import java.util.Optional;
import java.util.UUID;

public class AddressService implements AddressUseCases {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void createAddress(EventRequestDTO data, Event event) {
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEventId(event.getId());
    }

    public Optional<Address> findByEventId(UUID eventId) {
        return addressRepository.findByEventId(eventId);
    }
}
