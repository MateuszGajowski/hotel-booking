package pl.gajowski.mateusz.hotelbooking.customer

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import pl.gajowski.mateusz.hotelbooking.rest.error.BadRequestException

@RunWith(MockitoJUnitRunner.class)
class CustomerServiceSpec {

    @Mock
    CustomerRepository customerRepository

    @InjectMocks
    CustomerService customerService

    @Test(expected = BadRequestException.class)
    void testLoginAlreadyUsed() {
        Mockito.when(customerRepository.findOneByLogin(Mockito.any())).thenReturn(Optional.ofNullable(new CustomerEntity()))

        callRegisterCustomer()
    }

    @Test(expected = BadRequestException.class)
    void testEmailAlreadyUsed() {
        Mockito.when(customerRepository.findOneByEmailIgnoreCase(Mockito.any())).thenReturn(Optional.ofNullable(new CustomerEntity()))

        callRegisterCustomer()
    }

    def callRegisterCustomer() {
        customerService.registerCustomer(new RegisterCustomerReq("LOGIN", "NAME", "SURNAME",
                "123123123", "aaaa@aa.pl", "passwrd12"))
    }
}
