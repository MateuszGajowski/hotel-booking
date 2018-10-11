package pl.gajowski.mateusz.hotelbooking.room.dto;

import lombok.Data;
import pl.gajowski.mateusz.hotelbooking.hotel.dto.HotelDTO;
import pl.gajowski.mateusz.hotelbooking.room.type.dto.RoomTypeDTO;

@Data
public class RoomDTO {
    private long id;
    private String number;
    private int floor;
    private HotelDTO hotel;
    private RoomTypeDTO type;
}
