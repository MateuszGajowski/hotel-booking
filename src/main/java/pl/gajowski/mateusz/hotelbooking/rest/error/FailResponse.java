package pl.gajowski.mateusz.hotelbooking.rest.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.gajowski.mateusz.hotelbooking.rest.BasicResponse;

@Getter
@Setter
@ToString
public class FailResponse extends BasicResponse {
    private String title;
    private String message;

    public FailResponse(String title, String message) {
        super(Status.FAIL, null);
        this.title = title;
        this.message = message;
    }
}
