package com.flight.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private long id;
    private String flightNumber;
    private String airlineName;
    private String departureCity;
    private String arrivalCity;
    private Time departureTime;
    private Date departureDate;
    private Time arrivalTime;
    private Date arrivalDate;
    private String status;
}
