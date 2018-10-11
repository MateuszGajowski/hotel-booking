package pl.gajowski.mateusz.hotelbooking.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.gajowski.mateusz.hotelbooking.utils.RegexExp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCustomerReq {
    @NotBlank
    @Pattern(regexp = RegexExp.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String surname;

    @NotBlank
    @Pattern(regexp = RegexExp.PHONE_NUMBER_REGEX)
    private String phoneNumber;

    @Email
    @Size(max = 256)
    private String email;

    @Size(min = 8, max = 30)
    private String password;
}
