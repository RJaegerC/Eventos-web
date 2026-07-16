package architeture.hexagonal.adapters.inbound.services;

import architeture.hexagonal.application.usecases.CouponUseCases;
import architeture.hexagonal.models.coupon.Coupon;
import architeture.hexagonal.models.coupon.CouponRepository;
import architeture.hexagonal.models.coupon.CouponRequestDTO;
import architeture.hexagonal.models.event.Event;
import architeture.hexagonal.models.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImp implements CouponUseCases {

    private final CouponRepository couponRepository;
    private final EventRepository eventRepository;

    @Override
    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponData.code());
        coupon.setDiscount(couponData.discount());
        coupon.setValid(new Date(couponData.valid()));
        coupon.setEventId(event.getId());

        return coupon;
    }

    @Override
    public List<Coupon> consultCoupons(UUID eventId, Date currentDate) {
        return couponRepository.findByEventIdAndValidAfter(eventId, currentDate);
    }
}
