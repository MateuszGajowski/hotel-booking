package pl.gajowski.mateusz.hotelbooking.customer;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegisterCustomerRsp {
    private long id;
    private String token;
}
