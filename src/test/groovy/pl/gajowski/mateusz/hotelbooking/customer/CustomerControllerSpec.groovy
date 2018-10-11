package pl.gajowski.mateusz.hotelbooking.customer

import com.devskiller.jfairy.Fairy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import pl.gajowski.mateusz.hotelbooking.HotelBookingApplication
import pl.gajowski.mateusz.hotelbooking.utils.RequestGenerator
import spock.lang.Specification

@SpringBootTest(classes = HotelBookingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerSpec extends Specification {

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

    def 'should return token and id'() {
        given:
        def person = Fairy.create().person()
        def req = RequestGenerator.registerCustomerReq(person.email, "TEST_LOGIN", person.firstName,
                person.lastName, person.password, "123123123")

        when:
        def rsp = restTemplate.postForEntity("/api/customers", req, RegisterCustomerRsp.class)

        then:
        rsp.statusCode == HttpStatus.OK
        rsp.body.id == 1
        rsp.body.token != null
    }
}
