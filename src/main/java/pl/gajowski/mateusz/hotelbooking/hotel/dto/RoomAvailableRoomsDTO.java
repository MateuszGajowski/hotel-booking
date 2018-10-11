package pl.gajowski.mateusz.hotelbooking.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.gajowski.mateusz.hotelbooking.room.type.dto.RoomTypeDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAvailableRoomsDTO {
    private long id;
    private String number;
    private int floor;
    private int capacity;
    private RoomTypeDTO type;
}
