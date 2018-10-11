package pl.gajowski.mateusz.hotelbooking.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gajowski.mateusz.hotelbooking.booking.dto.BookingDTO;
import pl.gajowski.mateusz.hotelbooking.booking.dto.CreateBookingReq;
import pl.gajowski.mateusz.hotelbooking.booking.dto.GetBookingsRsp;
import pl.gajowski.mateusz.hotelbooking.rest.error.BadRequestException;
import pl.gajowski.mateusz.hotelbooking.utils.MDCUtils;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/customer/{id}/bookings")
    public ResponseEntity<?> getBookings(@PathVariable("id") Long customerId) {
        MDCUtils.put(MDCUtils.SOURCE, "customer.get.bookings");
        MDCUtils.put(MDCUtils.SUBJECT, "id", customerId);

        final GetBookingsRsp rsp = bookingService.getBookings(customerId);
        return ResponseEntity.ok(rsp);
    }

    @PutMapping("/customer/{customerId}/bookings/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable("customerId") Long customerId,
                                           @PathVariable("bookingId") Long bookingId) {
        MDCUtils.put(MDCUtils.SOURCE, "customer.cancel.booking");
        MDCUtils.put(MDCUtils.SUBJECT, "customerId", customerId);
        MDCUtils.put(MDCUtils.SUBJECT, "bookingId", bookingId);

        bookingService.cancelBooking(customerId, bookingId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/customer/{customerId}/bookings")
    public ResponseEntity<?> createBooking(@PathVariable("customerId") Long customerId,
                                           @Valid @RequestBody CreateBookingReq req) {
        MDCUtils.put(MDCUtils.SOURCE, "customer.create.booking");
        MDCUtils.put(MDCUtils.SUBJECT, "customerId", customerId);

        final LocalDate startDate = req.getStartDate();
        final LocalDate endDate = req.getEndDate();

        if (startDate.isAfter(endDate)) {
            throw new BadRequestException("Start date after end date");
        }

        final BookingDTO rsp = bookingService.createBooking(customerId, req);

        return ResponseEntity.status(HttpStatus.CREATED).body(rsp);
    }
}
