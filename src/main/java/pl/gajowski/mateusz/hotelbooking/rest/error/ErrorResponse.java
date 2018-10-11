package pl.gajowski.mateusz.hotelbooking.rest.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.gajowski.mateusz.hotelbooking.rest.BasicResponse;

@Getter
@Setter
@ToString
public class ErrorResponse extends BasicResponse {
    private String message;

    public ErrorResponse(String message) {
        super(Status.ERROR, null);
        this.message = message;
    }
}
