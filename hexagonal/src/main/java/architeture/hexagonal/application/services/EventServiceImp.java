package architeture.hexagonal.application.services;

import architeture.hexagonal.adapters.outbound.storage.ImageUploaderPort;
import architeture.hexagonal.application.usecases.EventUseCases;
import architeture.hexagonal.models.adress.Address;
import architeture.hexagonal.models.coupon.Coupon;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventAddressProjection;
import architeture.hexagonal.models.event.EventDetailsDTO;
import architeture.hexagonal.models.event.EventRepository;
import architeture.hexagonal.models.event.EventRequestDTO;
import architeture.hexagonal.models.event.EventResponseDTO;
import architeture.hexagonal.utils.mappers.EventMapper;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventServiceImp implements EventUseCases {

    private final String adminKey;
    private final AddressService addressService;
    private final CouponService couponService;
    private final EventRepository repository;
    private final ImageUploaderPort imageUploaderPort;
    private final EventMapper mapper;

    public EventServiceImp(
            String adminKey,
            AddressService addressService,
            CouponService couponService,
            EventRepository repository,
            ImageUploaderPort imageUploaderPort,
            EventMapper mapper
    ) {
        this.adminKey = adminKey;
        this.addressService = addressService;
        this.couponService = couponService;
        this.repository = repository;
        this.imageUploaderPort = imageUploaderPort;
        this.mapper = mapper;
    }

    @Override
    public Event createEvent(EventRequestDTO data) {
        String imgUrl = "";

        if (data.image() != null) {
            imgUrl = imageUploaderPort.uploadImage(data.image());
        }

        Event newEvent = mapper.dtoToEntity(data, imgUrl);
        repository.save(newEvent);

        if (Boolean.FALSE.equals(data.remote())) {
            addressService.createAddress(data, newEvent);
        }

        return newEvent;
    }

    @Override
    public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
        Page<EventAddressProjection> eventsPage =
                repository.findUpcomingEvents(page, size);

        return eventsPage.map(event -> new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getCity() != null ? event.getCity() : "",
                event.getUf() != null ? event.getUf() : "",
                event.getRemote(),
                event.getEventUrl(),
                event.getImgUrl()
        )).stream().toList();
    }

    @Override
    public EventDetailsDTO getEventDetails(UUID eventId) {
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Optional<Address> address =
                addressService.findByEventId(eventId);

        List<Coupon> coupons =
                couponService.consultCoupons(eventId, new Date());

        return mapper.domainToDetailsDto(event, address, coupons);
    }

    @Override
    public void deleteEvent(UUID eventId, String adminKey) {
        if (adminKey == null || !adminKey.equals(this.adminKey)) {
            throw new IllegalArgumentException("Invalid admin key");
        }

        repository.deleteById(eventId);
    }

    @Override
    public List<EventResponseDTO> searchEvents(String title) {
        title = (title != null) ? title : "";

        List<EventAddressProjection> eventsList =
                repository.findEventsByTitle(title);

        return eventsList.stream().map(event -> new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getCity() != null ? event.getCity() : "",
                event.getUf() != null ? event.getUf() : "",
                event.getRemote(),
                event.getEventUrl(),
                event.getImgUrl()
        )).toList();
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
        city = city != null ? city : "";
        uf = uf != null ? uf : "";
        startDate = startDate != null ? startDate : new Date(0);
        endDate = endDate != null ? endDate : new Date();

        Page<EventAddressProjection> eventsPage =
                repository.findFilteredEvents(
                        city,
                        uf,
                        startDate,
                        endDate,
                        page,
                        size
                );

        return eventsPage.map(event -> new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getCity() != null ? event.getCity() : "",
                event.getUf() != null ? event.getUf() : "",
                event.getRemote(),
                event.getEventUrl(),
                event.getImgUrl()
        )).stream().toList();
    }
}
