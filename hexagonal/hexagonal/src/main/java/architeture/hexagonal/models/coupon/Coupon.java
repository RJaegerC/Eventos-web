package architeture.hexagonal.models.coupon;

import java.util.Date;
import java.util.UUID;

public class Coupon {

    private UUID id;

    private String code;

    private Integer discount;

    private Date valid;

    private UUID eventId;

    public Coupon() {
    }

    public Coupon(UUID id, String code, Integer discount, Date valid, UUID eventId) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.valid = valid;
        this.eventId = eventId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Date getValid() {
        return valid;
    }

    public void setValid(Date valid) {
        this.valid = valid;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

}
