package architeture.hexagonal.models.adress;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository {

    Optional<Address> findByEventId(UUID eventId);

}
