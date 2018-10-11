package pl.gajowski.mateusz.hotelbooking.room.type.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomTypeDTO {
    private int capacity;
    private String roomClass;
    private BigDecimal price;
}
