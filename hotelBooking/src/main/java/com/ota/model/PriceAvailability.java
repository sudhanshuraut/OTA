package com.ota.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PriceAvailability {

    private Integer hotelID;

    private String date;

    private Integer availability;

    private Double onePerson;

    private Double twoPerson;

    public Integer getHotelID() {
        return hotelID;
    }

    public String getDate() {
        return date;
    }

    public Integer getAvailability() {
        return availability;
    }

    public Double getOnePerson() {
        return onePerson;
    }

    public Double getTwoPerson() {
        return twoPerson;
    }
}
