package com.ota.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "hotel_details")
public class HotelDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "hotel_id")
    private Integer hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "checkin_time")
    private String checkInTime;

    @Column(name = "checkout_time")
    private String checkOutTime;

    @Column(name = "hotel_contact")
    private String hotelContact;

    @Column(name = "cancellation_policy")
    private String cancellationPolicy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }


    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public Integer getHotelID() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getHotelContact() {
        return hotelContact;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public void setHotelContact(String hotelContact) {
        this.hotelContact = hotelContact;
    }
}

