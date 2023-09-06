package com.ota.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "added_date")
    private String date;

    @Column(name = "booker_name")
    private String bookerName;

    @Column(name = "number_of_person")
    private Integer numberOfPerson;

    @Column(name = "booking_amount")
    private Double bookingAmount;

    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "confirmation_number")
    private Integer confirmationNumber;


    public Integer getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(Integer confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookerName() {
        return bookerName;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public Integer getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(Integer numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    public Double getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(Double bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public Integer getHotelID() {
        return hotelId;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelId = hotelID;
    }
}
