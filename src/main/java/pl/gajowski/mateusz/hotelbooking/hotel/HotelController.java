package pl.gajowski.mateusz.hotelbooking.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.gajowski.mateusz.hotelbooking.hotel.dto.GetHotelsWithRoomsRsp;
import pl.gajowski.mateusz.hotelbooking.rest.error.BadRequestException;
import pl.gajowski.mateusz.hotelbooking.utils.MDCUtils;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/hotels/rooms")
@Validated
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<?> searchForRooms(@Valid @Future @RequestParam LocalDate startDate,
                                            @Valid @Future @RequestParam LocalDate endDate,
                                            @Valid @NotEmpty @RequestParam String city,
                                            @Valid @RequestParam BigDecimal priceRangeStart,
                                            @Valid @RequestParam BigDecimal priceRangeEnd) {
        MDCUtils.put(MDCUtils.SOURCE, "rooms.search");

        if (startDate.isAfter(endDate)) {
            throw new BadRequestException("Start date after end date");
        }

        final GetHotelsWithRoomsRsp rsp = hotelService.findAvailableRoomsInHotels(startDate, endDate, city, priceRangeStart, priceRangeEnd);
        return ResponseEntity.ok(rsp);
    }
}
