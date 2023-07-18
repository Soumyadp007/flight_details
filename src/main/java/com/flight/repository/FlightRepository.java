package com.flight.repository;

import com.flight.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Page<Flight> findByDepartureCityAndArrivalCityAndDepartureDate(Pageable departureCity, String arrivalCity, String departureDate, LocalDate pageable);

    Flight findByFlightNumber(String flightNumber);

}
