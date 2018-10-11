package pl.gajowski.mateusz.hotelbooking.rest.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String cause) {
        super(cause);
    }
}
