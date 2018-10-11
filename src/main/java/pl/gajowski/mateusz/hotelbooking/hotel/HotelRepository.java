package pl.gajowski.mateusz.hotelbooking.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
    @Query("SELECT h FROM hotel h JOIN FETCH h.rooms r LEFT JOIN FETCH r.bookings b WHERE h.city = :city AND " +
            "r.type.price >= :startPrice AND r.type.price <= :endPrice " +
            "AND (NOT EXISTS (SELECT b from b WHERE b.dateIn >= :dateIn AND b.dateOut <= :dateOut) OR b.status = 2)")

    List<HotelEntity> findByParams(@Param("city") String city, @Param("startPrice")BigDecimal startPrice, @Param("endPrice") BigDecimal endPrice,
                               @Param("dateIn") LocalDate dateIn, @Param("dateOut") LocalDate dateOut);
}
