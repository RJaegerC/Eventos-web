package architeture.hexagonal.models.coupon;

public record CouponRequestDTO(String code, Integer discount, Long valid) {
}