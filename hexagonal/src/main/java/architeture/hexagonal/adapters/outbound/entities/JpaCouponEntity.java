package architeture.hexagonal.adapters.outbound.entities;

import architeture.hexagonal.models.coupon.Coupon;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaCouponEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String code;
    private Integer discount;
    private Date valid;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private JpaEventEntity event;

    public JpaCouponEntity(Coupon coupon, JpaEventEntity event) {
        this.id = coupon.getId();
        this.code = coupon.getCode();
        this.discount = coupon.getDiscount();
        this.valid = coupon.getValid();
        this.event = event;
    }
}
