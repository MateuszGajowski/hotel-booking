package pl.gajowski.mateusz.hotelbooking.rest.error;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        this(null);
    }

    public BadRequestException(String cause) {
        super(cause);
    }
}
