package com.flight.service;

import com.flight.payload.FlightDto;
import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    FlightDto createFlight(FlightDto flightDto);

    List<FlightDto> getAllFlights(int pageNo,int pageSize, String sortBy, String sortDir);

    List<FlightDto> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate, int pageNo, int pageSize, String sortBy, String sortDir);

    FlightDto updateFlight(FlightDto flightDto, long id);

    FlightDto  updateFlightByFlightNumber(FlightDto flightDto, String flightId);

    void deleteFlightById(long id);

    FlightDto findByFlightNumber(String flightNumber);
}
