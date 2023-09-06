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
public class HotelDetailsRequest {

    private Integer hotelId;

    private String hotelName;

    private String checkInTime;

    private String checkOutTime;

    private String hotelContact;

    private String cancellationPolicy;

    public Integer getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public String getHotelContact() {
        return hotelContact;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }
}
