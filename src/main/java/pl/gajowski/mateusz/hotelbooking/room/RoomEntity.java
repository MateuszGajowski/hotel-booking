package pl.gajowski.mateusz.hotelbooking.room;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.gajowski.mateusz.hotelbooking.base.db.BaseEntity;
import pl.gajowski.mateusz.hotelbooking.booking.BookingEntity;
import pl.gajowski.mateusz.hotelbooking.hotel.HotelEntity;
import pl.gajowski.mateusz.hotelbooking.room.type.RoomTypeEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "room")
@Table(name = "ROOM")
@Getter
@Setter
@ToString
public class RoomEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private int floor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
    @OneToOne
    private RoomTypeEntity type;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private Set<BookingEntity> bookings;
}
