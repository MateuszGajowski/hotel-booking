package pl.gajowski.mateusz.hotelbooking.booking;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.gajowski.mateusz.hotelbooking.base.db.BaseEntity;
import pl.gajowski.mateusz.hotelbooking.customer.CustomerEntity;
import pl.gajowski.mateusz.hotelbooking.room.RoomEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "booking")
@Table(name = "BOOKING")
@Getter
@Setter
@ToString
public class BookingEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    @Column(nullable = false)
    private LocalDate dateIn;
    @Column(nullable = false)
    private LocalDate dateOut;
    @OneToOne
    private CustomerEntity customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private RoomEntity room;
    @Column(nullable = false)
    private String confirmationCode;
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Status status = Status.IN_PROCESS;

    public enum Status {
        IN_PROCESS,
        ACTIVE,
        CANCELED
    }
}
