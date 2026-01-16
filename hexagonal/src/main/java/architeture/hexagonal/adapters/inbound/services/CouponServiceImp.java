package architeture.hexagonal.adapters.inbound.services;

import architeture.hexagonal.application.services.CouponService;
import architeture.hexagonal.models.coupon.Coupon;
import architeture.hexagonal.models.coupon.CouponRepository;
import architeture.hexagonal.models.coupon.CouponRequestDTO;
import architeture.hexagonal.models.event.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CouponServiceImp {

    private final CouponService useCase;

    public CouponServiceImp(
            CouponRepository couponRepository,
            EventRepository eventRepository
    ) {
        this.useCase = new CouponService(
                couponRepository,
                eventRepository
        );
    }

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData) {
        return useCase.addCouponToEvent(eventId, couponData);
    }

    public List<Coupon> consultCoupons(UUID eventId, Date currentDate) {
        return useCase.consultCoupons(eventId, currentDate);
    }
}
