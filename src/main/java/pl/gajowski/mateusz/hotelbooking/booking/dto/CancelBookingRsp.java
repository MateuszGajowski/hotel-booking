package pl.gajowski.mateusz.hotelbooking.booking.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class CancelBookingRsp {
    private List<BookingDTO> bookings = new LinkedList<>();
}
