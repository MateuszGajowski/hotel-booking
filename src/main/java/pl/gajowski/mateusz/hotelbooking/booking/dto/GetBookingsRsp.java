package pl.gajowski.mateusz.hotelbooking.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingsRsp {
    private List<BookingDTO> bookings = new LinkedList<>();
}
