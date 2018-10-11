package pl.gajowski.mateusz.hotelbooking.hotel;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gajowski.mateusz.hotelbooking.hotel.dto.GetHotelsWithRoomsRsp;
import pl.gajowski.mateusz.hotelbooking.hotel.dto.HotelAvailableRoomsDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GetHotelsWithRoomsRsp findAvailableRoomsInHotels(LocalDate startDate, LocalDate endDate, String city, BigDecimal priceRangeStart,
                                                            BigDecimal priceRangeEnd) {
        List<HotelEntity> hotels = hotelRepository.findByParams(city, priceRangeStart, priceRangeEnd, startDate, endDate);
        log.info("Found rooms {}", hotels.size());

        return new GetHotelsWithRoomsRsp(modelMapper.map(hotels, new TypeToken<List<HotelAvailableRoomsDTO>>() {}.getType()));
    }
}
