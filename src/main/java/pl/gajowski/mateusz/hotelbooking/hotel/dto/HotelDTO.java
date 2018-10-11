package pl.gajowski.mateusz.hotelbooking.hotel.dto;

import lombok.Data;

@Data
public class HotelDTO {
    private String name;
    private String country;
    private String city;
    private String address;
    private String postalCode;
}
