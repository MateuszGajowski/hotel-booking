package pl.gajowski.mateusz.hotelbooking.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gajowski.mateusz.hotelbooking.utils.MDCUtils;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterCustomerReq req) {
        MDCUtils.put(MDCUtils.SUBJECT, "register.customer");
        return ResponseEntity.ok(customerService.registerCustomer(req));
    }
}
