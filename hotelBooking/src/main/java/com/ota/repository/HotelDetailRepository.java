package com.ota.repository;

import com.ota.entity.HotelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelDetailRepository extends JpaRepository<HotelDetails, Integer> {

    @Query(value = "SELECT h FROM HotelDetails h WHERE h.hotelId = :hotelId")
    List<HotelDetails> findByHotelId(@Param("hotelId") Integer hotelId);
}
