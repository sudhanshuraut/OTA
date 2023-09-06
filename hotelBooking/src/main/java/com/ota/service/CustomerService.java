package com.ota.service;

import com.ota.entity.Booking;
import com.ota.entity.HotelDetails;
import com.ota.entity.PriceAndAvailability;
import com.ota.exception.OTAException;
import com.ota.model.BookingRequest;
import com.ota.model.BookingResponse;
import com.ota.model.SearchCriteria;
import com.ota.model.SearchResponse;
import com.ota.repository.BookingRepository;
import com.ota.repository.HotelDetailRepository;
import com.ota.repository.PriceAndAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
public class CustomerService {

    private final Random random = new Random();

    @Autowired
    private PriceAndAvailabilityRepository priceAndAvailabilityRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelDetailRepository hotelDetailRepository;

    private static SearchResponse getSearchResponse(PriceAndAvailability priceAndAvailability) {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setAvailability(priceAndAvailability.getAvailability());
        searchResponse.setOnePerson(priceAndAvailability.getOnePerson());
        searchResponse.setTwoPerson(priceAndAvailability.getTwoPerson());
        return searchResponse;
    }

    public SearchResponse checkPriceAndAvailability(SearchCriteria searchCriteria) {
        List<PriceAndAvailability> priceAndAvailabilities = priceAndAvailabilityRepository.findByHotelIdAndDate(searchCriteria.getHotelId(), searchCriteria.getDate());
        if (priceAndAvailabilities.isEmpty()) {
            throw new OTAException();
        }
        PriceAndAvailability priceAndAvailability = priceAndAvailabilities.get(0);

        return getSearchResponse(priceAndAvailability);
    }

    public BookingResponse bookHotel(BookingRequest bookingRequest) {

        List<PriceAndAvailability> priceAndAvailabilities = priceAndAvailabilityRepository.findByHotelIdAndDate(bookingRequest.getHotelId(), bookingRequest.getDate());
        if (priceAndAvailabilities.isEmpty()) {
            throw new OTAException();
        }

        Booking booking = getBookingEntity(bookingRequest);
        bookingRepository.save(booking);

        updateAvailabilityAndDeleteIfZero(priceAndAvailabilities.get(0));

        List<HotelDetails> hotelDetails = hotelDetailRepository.findByHotelId(bookingRequest.getHotelId());

        return getBookingResponse(booking, hotelDetails.get(0));
    }

    @Transactional
    public void updateAvailabilityAndDeleteIfZero(PriceAndAvailability priceAndAvailability) {
        int currentAvailability = priceAndAvailability.getAvailability();

        // Check availability
        if (currentAvailability > 0) {
            // Decrement by 1
            priceAndAvailability.setAvailability(currentAvailability - 1);
        }

        // Check if availability has become zero
        if (priceAndAvailability.getAvailability() == 0) {
            // Throw exception only
            throw new OTAException();
        } else {
            // Otherwise, save the updated entity
            priceAndAvailabilityRepository.save(priceAndAvailability);
        }
    }

    private BookingResponse getBookingResponse(Booking booking, HotelDetails hotelDetails) {

        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setConfirmationNumber(booking.getConfirmationNumber());
        bookingResponse.setCancellationPolicy(hotelDetails.getCancellationPolicy());
        bookingResponse.setCheckInTime(hotelDetails.getCheckInTime());
        bookingResponse.setCheckOutTime(hotelDetails.getCheckOutTime());
        bookingResponse.setHotelContact(hotelDetails.getHotelContact());
        bookingResponse.setHotelName(hotelDetails.getHotelName());
        return bookingResponse;
    }

    private Booking getBookingEntity(BookingRequest bookingRequest) {

        int randomNumber = random.nextInt(1000000);

        Booking booking = new Booking();
        booking.setBookingAmount(bookingRequest.getBookingAmount());
        booking.setConfirmationNumber(randomNumber);
        booking.setBookerName(bookingRequest.getBookerName());
        booking.setDate(bookingRequest.getDate());
        booking.setNumberOfPerson(bookingRequest.getNumberOfPerson());
        booking.setHotelID(bookingRequest.getHotelId());
        return booking;
    }
}
