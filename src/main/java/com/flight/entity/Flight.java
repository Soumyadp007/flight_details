package com.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "flight_details")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name= "flight_number")
    private String flightNumber;
    @Column(name= "airline_name")
    private String airlineName;
    @Column(name= "departure_city")
    private String departureCity;
    @Column(name= "arrival_city")
    private String arrivalCity;
    @Column(name= "departure_time")
    private Time departureTime;
    @Column(name= "departure_date")
    private Date departureDate;
    @Column(name= "arrival_time")
    private Time arrivalTime;
    @Column(name= "arrival_date")
    private Date arrivalDate;
    @Column(name= "status")
    private String status;
}
