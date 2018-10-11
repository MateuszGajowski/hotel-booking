package pl.gajowski.mateusz.hotelbooking.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gajowski.mateusz.hotelbooking.rest.error.BadRequestException;

@Service
@Transactional
@Slf4j
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerTokenService customerTokenService;

    public RegisterCustomerRsp registerCustomer(RegisterCustomerReq req) {
        final String email = req.getEmail();
        final String login = req.getLogin();

        customerRepository.findOneByLogin(login).ifPresent(user -> {
            throw new BadRequestException("LOGIN_ALREADY_USED");
        });

        customerRepository.findOneByEmailIgnoreCase(email).ifPresent(user -> {
            throw new BadRequestException("EMAIL_ALREADY_USED");
        });

        final CustomerEntity customer = saveCustomer(email, login, req.getName(), req.getSurname(), req.getPassword(), req.getPhoneNumber());
        log.info("Succesfuly created customer with id={}", customer.getId());

        return new RegisterCustomerRsp(customer.getId(), customer.getToken());
    }

    private CustomerEntity saveCustomer(String email, String login, String name, String surname,
                                        String password, String phoneNumber) {
        final CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail(email);
        customerEntity.setLogin(login);
        customerEntity.setName(name);
        customerEntity.setSurname(surname);
        customerEntity.setPassword(passwordEncoder.encode(password));
        customerEntity.setPhoneNumber(phoneNumber);
        customerEntity.setToken(customerTokenService.generateToken());

        return customerRepository.save(customerEntity);
    }
}
