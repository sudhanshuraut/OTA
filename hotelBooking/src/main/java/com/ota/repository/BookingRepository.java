package com.ota.repository;

import com.ota.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "SELECT b FROM Booking b WHERE b.hotelId = :hotelId")
    List<Booking> findByHotelId(@Param("hotelId") Integer hotelId);
}
