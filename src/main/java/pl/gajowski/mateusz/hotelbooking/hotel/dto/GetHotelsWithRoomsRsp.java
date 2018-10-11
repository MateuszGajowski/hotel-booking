package pl.gajowski.mateusz.hotelbooking.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetHotelsWithRoomsRsp {
    private List<HotelAvailableRoomsDTO> hotels = new LinkedList<>();
}
