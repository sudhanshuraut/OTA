package com.ota.controller.hotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ota.entity.Booking;
import com.ota.model.HotelDetailsRequest;
import com.ota.model.PriceAvailability;
import com.ota.repository.BookingRepository;
import com.ota.repository.PriceAndAvailabilityRepository;
import com.ota.service.HotelierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Map;

@RestController
public class HotelierController {

    @Value("${api-key}")
    private String apiKey;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PriceAndAvailabilityRepository priceAndAvailabilityRepository;

    @Autowired
    private HotelierService hotelierService;

    @GetMapping(value = "/hotelier/bookings/{hotelId}")
    public ResponseEntity<String> fetchAllBooking(@PathVariable Integer hotelId, @RequestHeader Map<String, String> headerMap) {
        try {
            AuthenticateUser(headerMap);
            List<Booking> bookings = hotelierService.fetchBookings(hotelId);
            return new ResponseEntity<>(objectMapper.writeValueAsString(bookings), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("User not authorized to perform this operation", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("No Bookings Exist for this hotel", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/hotelier/update-price-availability", consumes = "application/json; charset=UTF-8")
    public ResponseEntity<String> updatePriceAndAvailability(@RequestBody PriceAvailability priceAvailability, @RequestHeader Map<String, String> headerMap) {
        try {
            AuthenticateUser(headerMap);
            hotelierService.updatePriceAvailability(priceAvailability);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("User not authorized to perform this operation", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Hotel Does Not exist", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/hotelier/add-details", consumes = "application/json; charset=UTF-8")
    public ResponseEntity<String> addHotelDetails(@RequestBody HotelDetailsRequest hotelDetailsRequest,  @RequestHeader Map<String, String> headerMap) {
        try {
            AuthenticateUser(headerMap);
            hotelierService.saveHotelDetails(hotelDetailsRequest);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("User not authorized to perform this operation", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void AuthenticateUser(Map<String, String> headerMap) throws AuthenticationException {
        if(!headerMap.get("x-api-key").equals(apiKey)) {
            throw new AuthenticationException();
        }
    }
}
