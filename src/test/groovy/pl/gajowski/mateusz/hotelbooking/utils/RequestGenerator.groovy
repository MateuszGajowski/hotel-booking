package pl.gajowski.mateusz.hotelbooking.utils

import pl.gajowski.mateusz.hotelbooking.booking.dto.CreateBookingReq
import pl.gajowski.mateusz.hotelbooking.customer.RegisterCustomerReq

import java.time.LocalDate

class RequestGenerator {
    static RegisterCustomerReq registerCustomerReq(String email, String login, String name, String surname,
                                                   String password, String phoneNumber) {
        RegisterCustomerReq req = new RegisterCustomerReq()
        req.email = email
        req.login = login
        req.name = name
        req.password = password
        req.phoneNumber = phoneNumber
        req.surname = surname
        req
    }

    static CreateBookingReq createBookingReq(Long roomId, LocalDate startDate, LocalDate endDate) {
        new CreateBookingReq(roomId, startDate, endDate)
    }
}
