package architeture.hexagonal.adapters.inbound.services;

import architeture.hexagonal.adapters.outbound.storage.ImageUploaderPort;
import architeture.hexagonal.application.services.AddressService;
import architeture.hexagonal.application.services.CouponService;
import architeture.hexagonal.application.services.EventService;
import architeture.hexagonal.application.usecases.EventUseCases;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventDetailsDTO;
import architeture.hexagonal.models.event.EventRequestDTO;
import architeture.hexagonal.models.event.EventResponseDTO;
import architeture.hexagonal.models.event.EventRepository;
import architeture.hexagonal.models.adress.AddressRepository;
import architeture.hexagonal.models.coupon.CouponRepository;
import architeture.hexagonal.models.event.EventRepository;
import architeture.hexagonal.utils.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImp implements EventUseCases {

    private final EventUseCases useCase;

    public EventServiceImp(
            @Value("${admin.key}") String adminKey,
            EventRepository eventRepository,
            AddressRepository addressRepository,
            CouponRepository couponRepository,
            ImageUploaderPort imageUploaderPort,
            EventMapper mapper
    ) {

        AddressService addressService =
                new AddressService(addressRepository);

        CouponService couponService =
                new CouponService(couponRepository, eventRepository);

        this.useCase = new EventService(
                adminKey,
                eventRepository,
                addressService,
                couponService,
                imageUploaderPort,
                mapper
        );
    }

    @Override
    public Event createEvent(EventRequestDTO data) {
        return useCase.createEvent(data);
    }

    @Override
    public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
        return useCase.getUpcomingEvents(page, size);
    }

    @Override
    public EventDetailsDTO getEventDetails(UUID id) {
        return useCase.getEventDetails(id);
    }

    @Override
    public void deleteEvent(UUID id, String adminKey) {
        useCase.deleteEvent(id, adminKey);
    }

    @Override
    public List<EventResponseDTO> searchEvents(String title) {
        return useCase.searchEvents(title);
    }

    @Override
    public List<EventResponseDTO> getFilteredEvents(
            int page,
            int size,
            String city,
            String uf,
            Date startDate,
            Date endDate
    ) {
        return useCase.getFilteredEvents(page, size, city, uf, startDate, endDate);
    }
}
