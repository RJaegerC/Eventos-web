package architeture.hexagonal.adapters.outbound.entities;

import architeture.hexagonal.models.adress.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaAddressEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String uf;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private JpaEventEntity event;

    public JpaAddressEntity(Address address, JpaEventEntity event) {
        this.id = address.getId();
        this.city = address.getCity();
        this.uf = address.getUf();
        this.event = event;
    }
}
