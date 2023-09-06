package com.ota.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SearchResponse {
    private Integer availability;
    private Double onePerson;
    private Double twoPerson;

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public void setOnePerson(Double onePerson) {
        this.onePerson = onePerson;
    }

    public void setTwoPerson(Double twoPerson) {
        this.twoPerson = twoPerson;
    }
}

