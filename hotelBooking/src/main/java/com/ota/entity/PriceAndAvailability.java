package com.ota.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "price_availability")
public class PriceAndAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "hotel_id")
    private Integer hotelId;
    @Column(name = "date")
    private String date;
    @Column(name = "availability")
    private Integer availability;
    @Column(name = "one_person")
    private Double onePerson;
    @Column(name = "two_person")
    private Double twoPerson;

    public Integer getId() {
        return id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Double getOnePerson() {
        return onePerson;
    }

    public void setOnePerson(Double onePerson) {
        this.onePerson = onePerson;
    }

    public Double getTwoPerson() {
        return twoPerson;
    }

    public void setTwoPerson(Double twoPerson) {
        this.twoPerson = twoPerson;
    }

    public void setHotelID(Integer hotelId) {
        this.hotelId = hotelId;
    }
}
