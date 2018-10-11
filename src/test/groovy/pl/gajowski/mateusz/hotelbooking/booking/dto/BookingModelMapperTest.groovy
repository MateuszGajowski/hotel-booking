package pl.gajowski.mateusz.hotelbooking.booking.dto

import org.junit.Test
import org.modelmapper.ModelMapper
import pl.gajowski.mateusz.hotelbooking.booking.BookingEntity
import pl.gajowski.mateusz.hotelbooking.hotel.HotelEntity
import pl.gajowski.mateusz.hotelbooking.room.RoomEntity

import java.time.LocalDate

class BookingModelMapperTest {
    private ModelMapper modelMapper = new ModelMapper()

    @Test
    void 'convert bookingEntity to DTO correctly'() {

        BookingEntity bookingEntity = new BookingEntity()
        bookingEntity.id = 1
        bookingEntity.dateIn = LocalDate.now()
        bookingEntity.status = BookingEntity.Status.ACTIVE

        RoomEntity roomEntity = new RoomEntity()
        roomEntity.floor = 2
        roomEntity.number = "201A"

        HotelEntity hotelEntity = new HotelEntity()
        hotelEntity.id = 3
        hotelEntity.name = "Test"

        bookingEntity.room = roomEntity
        bookingEntity.room.hotel = hotelEntity

        BookingDTO dto = modelMapper.map(bookingEntity, BookingDTO.class)
        assert dto.id == bookingEntity.id.toInteger()
        assert dto.room.floor == roomEntity.floor
        assert dto.room.hotel.name == hotelEntity.name
        assert dto != null
    }

}
