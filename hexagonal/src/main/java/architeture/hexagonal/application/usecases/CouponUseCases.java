package architeture.hexagonal.application.usecases;

import architeture.hexagonal.models.coupon.Coupon;
import architeture.hexagonal.models.coupon.CouponRequestDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CouponUseCases {
    Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData);
    List<Coupon> consultCoupons(UUID eventId, Date currentDate);
}
