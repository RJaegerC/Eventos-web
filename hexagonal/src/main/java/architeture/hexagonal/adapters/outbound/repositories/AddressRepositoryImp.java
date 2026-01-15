package architeture.hexagonal.adapters.outbound.repositories;

import architeture.hexagonal.adapters.outbound.entities.JpaAddressEntity;
import architeture.hexagonal.models.adress.Address;
import architeture.hexagonal.models.adress.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

import java.util.stream.Collectors;

@Repository
public class AddressRepositoryImp implements AddressRepository {

    private final JpaAddressRepository jpaAddressRepository;

    @Autowired
    public AddressRepositoryImp(JpaAddressRepository jpaAddressRepository) {
        this.jpaAddressRepository = jpaAddressRepository;
    }

    @Override
    public Optional<Address> findByEventId(UUID id) {
        return this.jpaAddressRepository.findByEventId(id)
                .map(this::toDomain);
    }

    private Address toDomain(JpaAddressEntity entity) {
        return new Address(
                entity.getId(),
                entity.getCity(),
                entity.getUf(),
                entity.getEvent().getId()
        );

    }

}
