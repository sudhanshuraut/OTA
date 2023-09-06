package com.ota.repository;

import com.ota.entity.PriceAndAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceAndAvailabilityRepository extends JpaRepository<PriceAndAvailability, Integer> {

    @Query(value = "SELECT p FROM PriceAndAvailability p WHERE p.hotelId = :hotelId AND p.date = :date")
    List<PriceAndAvailability> findByHotelIdAndDate(@Param("hotelId") Integer hotelId, @Param("date") String date);
}
