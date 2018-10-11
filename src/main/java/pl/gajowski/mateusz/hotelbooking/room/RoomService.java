package pl.gajowski.mateusz.hotelbooking.room;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public void findAvailableRooms(LocalDate startDate, LocalDate endDate, String city, BigDecimal priceRangeStart,
                                   BigDecimal priceRangeEnd) {
        List<RoomEntity> rooms = roomRepository.findByPeriodCityPriceRange(city, startDate);
        log.info("Found rooms {}", rooms.size());
    }

    public Optional<RoomEntity> findAvailableRoom(Long roomId, LocalDate dateIn, LocalDate dateOut) {
       return roomRepository.findAvailableRoom(roomId, dateIn, dateOut);
    }
}
