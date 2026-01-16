package architeture.hexagonal.utils.mappers;

import architeture.hexagonal.adapters.outbound.entities.JpaEventEntity;
import architeture.hexagonal.models.adress.Address;
import architeture.hexagonal.models.coupon.Coupon;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventDetailsDTO;
import architeture.hexagonal.models.event.EventRequestDTO;
import architeture.hexagonal.models.event.EventResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dto.title", target = "title")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "imgUrl", target = "imgUrl")
    @Mapping(source = "dto.eventUrl", target = "eventUrl")
    @Mapping(source = "dto.date", target = "date", qualifiedByName = "epochToDate")
    @Mapping(source = "dto.remote", target = "remote")
    Event dtoToEntity(EventRequestDTO dto, String imgUrl);

    @Mapping(source = "entity.title", target = "title")
    @Mapping(source = "entity.description", target = "description")
    @Mapping(source = "entity.eventUrl", target = "eventUrl")
    @Mapping(source = "entity.date", target = "date", qualifiedByName = "dateToEpoch")
    @Mapping(source = "entity.remote", target = "remote")
    EventRequestDTO toDto(Event entity);

    default Event jpaToDomain(JpaEventEntity jpa) {
        if (jpa == null) return null;
        return new Event(
                jpa.getId(),
                jpa.getTitle(),
                jpa.getDescription(),
                jpa.getImgUrl(),
                jpa.getEventUrl(),
                jpa.getRemote(),
                jpa.getDate()
        );
    }

    default EventDetailsDTO domainToDetailsDto(Event event, Optional<Address> address, List<Coupon> coupons) {
        String city = address.map(Address::getCity).orElse("");
        String uf = address.map(Address::getUf).orElse("");
        List<EventDetailsDTO.CouponDTO> couponDTOs = coupons.stream()
                .map(coupon -> new EventDetailsDTO.CouponDTO(
                        coupon.getCode(),
                        coupon.getDiscount(),
                        coupon.getValid()))
                .collect(Collectors.toList());

        return new EventDetailsDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                city,
                uf,
                event.getImgUrl(),
                event.getEventUrl(),
                couponDTOs
        );
    }

    @Named("epochToDate")
    default Date epochToDate(Long timestamp) {
        return timestamp != null ? new Date(timestamp) : null;
    }

    @Named("dateToEpoch")
    default Long dateToEpoch(Date date) {
        return date != null ? date.getTime() : null;
    }
}
