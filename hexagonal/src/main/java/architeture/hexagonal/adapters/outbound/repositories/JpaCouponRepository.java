package architeture.hexagonal.adapters.outbound.repositories;


import architeture.hexagonal.adapters.outbound.entities.JpaCouponEntity;
import architeture.hexagonal.models.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface JpaCouponRepository extends JpaRepository<JpaCouponEntity, UUID> {

    List<Coupon> findByEventIdAndValidAfter(UUID eventId, Date currentDate);

}
