package pl.gajowski.mateusz.hotelbooking.booking;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gajowski.mateusz.hotelbooking.booking.dto.BookingDTO;
import pl.gajowski.mateusz.hotelbooking.booking.dto.CreateBookingReq;
import pl.gajowski.mateusz.hotelbooking.booking.dto.GetBookingsRsp;
import pl.gajowski.mateusz.hotelbooking.customer.CustomerEntity;
import pl.gajowski.mateusz.hotelbooking.customer.CustomerRepository;
import pl.gajowski.mateusz.hotelbooking.rest.error.BadRequestException;
import pl.gajowski.mateusz.hotelbooking.rest.error.NotFoundException;
import pl.gajowski.mateusz.hotelbooking.room.RoomEntity;
import pl.gajowski.mateusz.hotelbooking.room.RoomService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ModelMapper modelMapper;

    public GetBookingsRsp getBookings(long customerId) {
        final List<BookingEntity> bookings = bookingRepository.findByCustomerId(customerId);
        log.info("Found {} customer bookings", bookings.size());

        return new GetBookingsRsp(modelMapper.map(bookings, new TypeToken<List<BookingDTO>>() {}.getType()));
    }

    public void cancelBooking(Long customerId, Long bookingId) {
        final BookingEntity bookingEntity = bookingRepository.findById(bookingId).orElseThrow(() ->
                new BadRequestException("Could not find booking with following id=" + bookingId));

        if (bookingEntity.getCustomer().getId() != customerId) {
            throw new BadRequestException("Trying to cancel another customer booking");
        }

        final BookingEntity.Status previousStatus = bookingEntity.getStatus();
        bookingEntity.setStatus(BookingEntity.Status.CANCELED);

        final BookingEntity updatedEntity = bookingRepository.save(bookingEntity);
        log.info("Successfully updated booking status from={}, to={}", previousStatus, updatedEntity.getStatus());
    }

    public BookingDTO createBooking(Long customerId, @Valid CreateBookingReq req) {
        final CustomerEntity customer = customerRepository.findById(customerId).<BadRequestException>orElseThrow(() -> {
            throw new BadRequestException("Could not find customer with id=" + customerId);
        });

        final RoomEntity room = roomService.findAvailableRoom(req.getRoomId(), req.getStartDate(), req.getEndDate()).<NotFoundException>orElseThrow(() -> {
            throw new NotFoundException("Could not find room with id=" + req.getRoomId());
        });

        log.info("Successfully acquired room for booking with id={}", room.getId());

        final BookingEntity booking = new BookingEntity();
        booking.setCustomer(customer);
        booking.setDateIn(req.getStartDate());
        booking.setDateOut(req.getEndDate());
        booking.setRoom(room);
        booking.setConfirmationCode(UUID.randomUUID().toString().replace("-", ""));

        final BookingEntity savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);
    }
}
