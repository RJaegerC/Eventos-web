package architeture.hexagonal.adapters.inbound.controllers;

import architeture.hexagonal.application.usecases.CouponUseCases;
import architeture.hexagonal.models.coupon.Coupon;
import architeture.hexagonal.models.coupon.CouponRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponUseCases couponUseCases;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> addCouponsToEvent(@PathVariable UUID eventId, @RequestBody CouponRequestDTO data) {
        Coupon coupons = couponUseCases.addCouponToEvent(eventId, data);
        return ResponseEntity.ok(coupons);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Coupon>> getCoupons(@PathVariable UUID eventId, @RequestParam Date currentDate) {
        List<Coupon> coupons = couponUseCases.consultCoupons(eventId, currentDate);
        return ResponseEntity.ok(coupons);
    }
}
