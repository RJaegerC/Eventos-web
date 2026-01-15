package architeture.hexagonal.models.adress;

import java.util.UUID;

public class Address {

    private UUID id;

    private String city;

    private String uf;

    private UUID eventId;

    public Address() {
    }

    public Address(UUID id, String city, String uf, UUID eventId) {
        this.id = id;
        this.city = city;
        this.uf = uf;
        this.eventId = eventId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }
}
