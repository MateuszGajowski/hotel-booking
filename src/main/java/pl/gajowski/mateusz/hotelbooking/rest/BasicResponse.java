package pl.gajowski.mateusz.hotelbooking.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BasicResponse<T> {
    private Status status;
    private T data;

    public enum Status {
        SUCCESS,
        FAIL,
        ERROR
    }

    public static <T> BasicResponse<T> createSuccess(T data) {
        return new BasicResponse<>(Status.SUCCESS, data);
    }
}
