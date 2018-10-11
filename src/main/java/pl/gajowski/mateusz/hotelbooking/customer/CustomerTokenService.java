package pl.gajowski.mateusz.hotelbooking.customer;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerTokenService {

    //Overly simplified for user identification
    //Normally JWT
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
