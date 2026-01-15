package architeture.hexagonal.adapters.outbound.repositories;

import architeture.hexagonal.adapters.outbound.entities.JpaCouponEntity;
import architeture.hexagonal.models.coupon.Coupon;
import architeture.hexagonal.models.coupon.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;

@Repository
public class CouponRepositoryImp implements CouponRepository {

    private final JpaCouponRepository jpaCouponRepository;

    @Autowired
    public CouponRepositoryImp(JpaCouponRepository jpaCouponRepository) {
        this.jpaCouponRepository = jpaCouponRepository;
    }

    @Override
    public List<Coupon> findByEventIdAndValidAfter(UUID eventId, Date currentDate) {
        return this.jpaCouponRepository.findByEventIdAndValidAfter(eventId, currentDate)
                .map(this::toDomain);
    }

    private Coupon toDomain(JpaCouponEntity entity) {
        return new Coupon(
                entity.getId(),
                entity.getCode(),
                entity.getDiscount(),
                entity.getValid(),
                entity.getEvent().getId()
        );
    }


}
