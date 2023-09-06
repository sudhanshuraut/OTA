package com.ota.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SearchCriteria {
    @NonNull
    private Integer hotelId;

    @NonNull
    private String date;

    @NonNull
    private String numberOfAdults;

    public Integer getHotelId() {
        return hotelId;
    }

    public String getDate() {
        return date;
    }

    public String getNumberOfAdults() {
        return numberOfAdults;
    }
}
