package pl.gajowski.mateusz.hotelbooking

import com.devskiller.jfairy.Fairy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.web.util.UriComponentsBuilder
import pl.gajowski.mateusz.hotelbooking.booking.BookingEntity
import pl.gajowski.mateusz.hotelbooking.booking.dto.BookingDTO
import pl.gajowski.mateusz.hotelbooking.booking.dto.GetBookingsRsp
import pl.gajowski.mateusz.hotelbooking.customer.RegisterCustomerRsp
import pl.gajowski.mateusz.hotelbooking.hotel.dto.GetHotelsWithRoomsRsp
import pl.gajowski.mateusz.hotelbooking.utils.RequestGenerator
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest(classes = HotelBookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AcceptanceTestSpec extends Specification {

    @Autowired
    private TestRestTemplate restTemplate

    def 'should return invalid request'() {
        given:
        def person = Fairy.create().person()
        def req = RequestGenerator.registerCustomerReq("aa", person.middleName, person.firstName,
                person.lastName, person.password, person.telephoneNumber)

        when:
        def rsp = restTemplate.postForEntity("/api/customers", req, RegisterCustomerRsp.class)

        then:
        rsp.statusCode == HttpStatus.BAD_REQUEST
    }

    def 'should do successful flow'() {
        given:
        def person = Fairy.create().person()
        def req = RequestGenerator.registerCustomerReq(person.email, "TEST_LOGIN", person.firstName,
                person.lastName, person.password, "123123123")

        when:
        def customerRsp = restTemplate.postForEntity("/api/customers", req, RegisterCustomerRsp.class)

        then:
        customerRsp.statusCode == HttpStatus.OK
        customerRsp.body.id == 1
        customerRsp.body.token != null

        when:
        def bookingsRsp = callGetBookings(1L)

        then:
        bookingsRsp.statusCode == HttpStatus.OK
        bookingsRsp.body.bookings.size() == 0

        when:
        def availableRoomsRsp = callGetAvailableRooms(LocalDate.now().plusDays(2), LocalDate.now().plusDays(5), "Katowice",
                new BigDecimal(60), new BigDecimal(100))

        then:
        availableRoomsRsp.statusCode == HttpStatus.OK
        availableRoomsRsp.body.hotels.size() == 1

        when:
        def createBookingRsp = callCreateBooking(1L, 1L,
                LocalDate.now().plusDays(2), LocalDate.now().plusDays(5))

        then:
        createBookingRsp.statusCode == HttpStatus.CREATED
        createBookingRsp.body.confirmationCode != null
        createBookingRsp.body.id == 1
        def confirmationCode = createBookingRsp.body.confirmationCode
        def bookingId = createBookingRsp.body.id

        when:
        def bookingsAfterRsp = callGetBookings(1L)

        then:
        bookingsAfterRsp.statusCode == HttpStatus.OK
        bookingsAfterRsp.body.bookings.size() == 1
        bookingsAfterRsp.body.bookings[0].confirmationCode == confirmationCode

        when:
        def availableRoomsAfterBookedRsp = callGetAvailableRooms(LocalDate.now().plusDays(2), LocalDate.now().plusDays(5), "Katowice",
                new BigDecimal(60), new BigDecimal(100))

        then:
        availableRoomsAfterBookedRsp.statusCode == HttpStatus.OK
        availableRoomsAfterBookedRsp.body.hotels.size() == 0

        when:
        def cancelBookingRsp = callCancelBooking(1L, bookingId)
        def bookingsAfterCancelRsp = callGetBookings(1L)

        then:
        bookingsAfterCancelRsp.statusCode == HttpStatus.OK
        bookingsAfterCancelRsp.body.bookings.size() == 1
        bookingsAfterCancelRsp.body.bookings[0].status == BookingEntity.Status.CANCELED
    }


    def callGetBookings(long id) {
        return restTemplate.getForEntity("/api/customer/{id}/bookings", GetBookingsRsp.class, id)
    }

    def callCreateBooking(Long customerId, Long roomId, LocalDate startDate, LocalDate endDate) {
        return restTemplate.postForEntity("/api/customer/{id}/bookings",
                RequestGenerator.createBookingReq(roomId, startDate, endDate), BookingDTO.class, customerId)
    }

    def callCancelBooking(Long customerId, Long bookingId) {
        return restTemplate.put("/api/customer/{id}/bookings/{bookingId}", GetBookingsRsp.class, customerId, bookingId)
    }

    def callGetAvailableRooms(LocalDate startDate, LocalDate endDate, String city, BigDecimal priceRangeStart,
                              BigDecimal priceRangeEnd) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("/api/hotels/rooms")
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .queryParam("city", city)
                .queryParam("priceRangeStart", priceRangeStart)
                .queryParam("priceRangeEnd", priceRangeEnd)

        return restTemplate.getForEntity(builder.buildAndExpand().toUri(), GetHotelsWithRoomsRsp.class)
    }
}