package architeture.hexagonal.models.coupon;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CouponRepository {

    List<Coupon> findByEventIdAndValidAfter(UUID eventId, Date currentDate);

}
