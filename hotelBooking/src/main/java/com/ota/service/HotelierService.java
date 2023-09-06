package com.ota.service;

import com.ota.entity.Booking;
import com.ota.entity.HotelDetails;
import com.ota.entity.PriceAndAvailability;
import com.ota.exception.OTAException;
import com.ota.model.HotelDetailsRequest;
import com.ota.model.PriceAvailability;
import com.ota.repository.BookingRepository;
import com.ota.repository.HotelDetailRepository;
import com.ota.repository.PriceAndAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelierService {

    @Autowired
    private HotelDetailRepository hotelDetailRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PriceAndAvailabilityRepository priceAndAvailabilityRepository;

    private static PriceAndAvailability getPriceAndAvailability(PriceAvailability priceAvailability) {
        PriceAndAvailability priceAndAvailability = new PriceAndAvailability();
        priceAndAvailability.setAvailability(priceAvailability.getAvailability());
        priceAndAvailability.setDate(priceAvailability.getDate());
        priceAndAvailability.setOnePerson(priceAvailability.getOnePerson());
        priceAndAvailability.setTwoPerson(priceAvailability.getTwoPerson());
        priceAndAvailability.setHotelID(priceAvailability.getHotelID());
        return priceAndAvailability;
    }

    public void saveHotelDetails(HotelDetailsRequest hotelDetailsRequest) {

        List<HotelDetails> hotelDetails = hotelDetailRepository.findByHotelId(hotelDetailsRequest.getHotelId());
        HotelDetails hotelDetailsEntity = getHotelDetailsEntity(hotelDetailsRequest);

        if (hotelDetails.isEmpty()) {
            hotelDetailRepository.save(hotelDetailsEntity);
            return;
        }
        hotelDetailRepository.delete(hotelDetails.get(0));
        hotelDetailRepository.save(hotelDetailsEntity);
    }

    private HotelDetails getHotelDetailsEntity(HotelDetailsRequest hotelDetailsRequest) {

        HotelDetails hotelDetails = new HotelDetails();
        hotelDetails.setCancellationPolicy(hotelDetailsRequest.getCancellationPolicy());
        hotelDetails.setCheckInTime(hotelDetailsRequest.getCheckInTime());
        hotelDetails.setCheckOutTime(hotelDetailsRequest.getCheckOutTime());
        hotelDetails.setHotelContact(hotelDetailsRequest.getHotelContact());
        hotelDetails.setHotelName(hotelDetailsRequest.getHotelName());
        hotelDetails.setHotelId(hotelDetailsRequest.getHotelId());
        return hotelDetails;
    }

    public List<Booking> fetchBookings(Integer hotelId) {
        List<Booking> bookings = bookingRepository.findByHotelId(hotelId);
        if (bookings.isEmpty()) {
            throw new OTAException();
        }
        return bookings;
    }

    public void updatePriceAvailability(PriceAvailability priceAvailability) {
        List<HotelDetails> hotelDetails = hotelDetailRepository.findByHotelId(priceAvailability.getHotelID());
        if (hotelDetails.isEmpty()) {
            throw new OTAException();
        }
        PriceAndAvailability priceAndAvailability = getPriceAndAvailability(priceAvailability);

        List<PriceAndAvailability> priceAndAvailabilities = priceAndAvailabilityRepository.findByHotelIdAndDate(priceAvailability.getHotelID(), priceAvailability.getDate());
        if (!priceAndAvailabilities.isEmpty()) {
            priceAndAvailabilityRepository.delete(priceAndAvailabilities.get(0));
        }
        priceAndAvailabilityRepository.save(priceAndAvailability);
    }
}
