package pl.gajowski.mateusz.hotelbooking.booking.dto;

import lombok.Data;
import pl.gajowski.mateusz.hotelbooking.booking.BookingEntity;
import pl.gajowski.mateusz.hotelbooking.room.dto.RoomDTO;

import java.time.LocalDate;

@Data
public class BookingDTO {
    private int id;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private RoomDTO room;
    private BookingEntity.Status status;
    private String confirmationCode;
}
