package com.ota.controller.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ota.model.BookingRequest;
import com.ota.model.BookingResponse;
import com.ota.model.SearchCriteria;
import com.ota.model.SearchResponse;
import com.ota.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Map;

@Slf4j
@RestController
public class CustomerController {

    @Value("${api-key}")
    private String apiKey;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/customer/check-price-availability", consumes = "application/json; charset=UTF-8", produces = "application/json")
    public ResponseEntity<String> checkPriceAndAvailability(@RequestBody SearchCriteria searchCriteria, @RequestHeader Map<String, String> headerMap) {
        try {
            AuthenticateUser(headerMap);
            SearchResponse searchResponse = customerService.checkPriceAndAvailability(searchCriteria);
            return ResponseEntity.ok().body(objectMapper.writeValueAsString(searchResponse));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("User not authorized to perform this operation", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Hotel Does Not exist or Rooms not available");
        }
    }

    @PostMapping(value = "/customer/book", consumes = "application/json; charset=UTF-8")
    public ResponseEntity<String> bookHotel(@RequestBody BookingRequest bookingRequest, @RequestHeader Map<String, String> headerMap) {
        try {
            AuthenticateUser(headerMap);
            BookingResponse bookingResponse = customerService.bookHotel(bookingRequest);
            return new ResponseEntity<>(objectMapper.writeValueAsString(bookingResponse), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("User not authorized to perform this operation", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>("Rooms not available, booking failed", HttpStatus.OK);
        }
    }

    private void AuthenticateUser(Map<String, String> headerMap) throws AuthenticationException {
        if(!headerMap.get("x-api-key").equals(apiKey)) {
            throw new AuthenticationException();
        }
    }
}
