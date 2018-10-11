package pl.gajowski.mateusz.hotelbooking.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingReq {
    @NotNull
    private Long roomId;
    @NotNull
    @Future
    private LocalDate startDate;
    @Future
    @NotNull
    private LocalDate endDate;
}
