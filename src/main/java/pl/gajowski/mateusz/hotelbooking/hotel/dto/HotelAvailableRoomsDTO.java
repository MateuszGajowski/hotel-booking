package pl.gajowski.mateusz.hotelbooking.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelAvailableRoomsDTO {
    private String name;
    private String country;
    private String city;
    private String address;
    private String postalCode;
    private List<RoomAvailableRoomsDTO> rooms;
}
