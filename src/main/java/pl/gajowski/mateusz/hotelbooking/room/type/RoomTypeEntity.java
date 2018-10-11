package pl.gajowski.mateusz.hotelbooking.room.type;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.gajowski.mateusz.hotelbooking.base.db.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ROOM_TYPE")
@Getter
@Setter
@ToString
public class RoomTypeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int capacity;
    @Column(name = "class", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Class roomClass;
    @Column(nullable = false)
    private BigDecimal price;

    public enum Class {
        ECONOMIC,
        STANDARD,
        PREMIUM
    }
}
