package com.ota.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BookingResponse {
    private Integer confirmationNumber;

    private String checkInTime;

    private String checkOutTime;

    private String hotelContact;

    private String cancellationPolicy;

    private String hotelName;


    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setConfirmationNumber(Integer confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public void setHotelContact(String hotelContact) {
        this.hotelContact = hotelContact;
    }
}
