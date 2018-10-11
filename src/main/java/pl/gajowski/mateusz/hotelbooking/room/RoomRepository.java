package pl.gajowski.mateusz.hotelbooking.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Query("SELECT r FROM room r JOIN r.hotel h JOIN r.bookings b WHERE h.city = (:pCity) AND b.dateOut > (:pDateIn)")
    List<RoomEntity> findByPeriodCityPriceRange(@Param("pCity") String city, @Param("pDateIn") LocalDate dateIn);

    @Query("SELECT r FROM room r WHERE r.id = :id AND NOT EXISTS (SELECT b from r.bookings b WHERE b.dateIn >= :dateIn AND b.dateOut <= :dateOut AND b.status != 2)")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RoomEntity> findAvailableRoom(@Param("id") long id, @Param("dateIn") LocalDate dateIn, @Param("dateOut") LocalDate dateOut);
}
