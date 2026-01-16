package architeture.hexagonal.utils.mappers;

import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventRequestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T23:48:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Arch Linux)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event dtoToEntity(EventRequestDTO dto, String imgUrl) {
        if ( dto == null && imgUrl == null ) {
            return null;
        }

        Event event = new Event();

        if ( dto != null ) {
            event.setTitle( dto.title() );
            event.setDescription( dto.description() );
            event.setEventUrl( dto.eventUrl() );
            event.setDate( epochToDate( dto.date() ) );
            event.setRemote( dto.remote() );
        }
        event.setImgUrl( imgUrl );

        return event;
    }

    @Override
    public EventRequestDTO toDto(Event entity) {
        if ( entity == null ) {
            return null;
        }

        String title = null;
        String description = null;
        String eventUrl = null;
        Long date = null;
        Boolean remote = null;

        title = entity.getTitle();
        description = entity.getDescription();
        eventUrl = entity.getEventUrl();
        date = dateToEpoch( entity.getDate() );
        remote = entity.getRemote();

        String city = null;
        String state = null;
        MultipartFile image = null;

        EventRequestDTO eventRequestDTO = new EventRequestDTO( title, description, date, city, state, remote, eventUrl, image );

        return eventRequestDTO;
    }
}
