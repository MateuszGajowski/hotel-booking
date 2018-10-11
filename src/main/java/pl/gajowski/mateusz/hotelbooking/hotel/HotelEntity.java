package pl.gajowski.mateusz.hotelbooking.hotel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.gajowski.mateusz.hotelbooking.base.db.BaseEntity;
import pl.gajowski.mateusz.hotelbooking.room.RoomEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "hotel")
@Table(name = "HOTEL")
@Getter
@Setter
@ToString
public class HotelEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String countryCode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String postalCode;
    @OneToMany(mappedBy = "hotel")
    private Set<RoomEntity> rooms;
}
