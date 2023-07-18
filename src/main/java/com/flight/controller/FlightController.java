package com.flight.controller;

import com.flight.payload.FlightDto;
import com.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto){
        FlightDto dto = flightService.createFlight(flightDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    public List<FlightDto> getAllFlight(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5" , required = false) int pageSize,
            @RequestParam(value = "sortby", required = false, defaultValue = "id")String sortBy,
            @RequestParam(value = "sortDir" , required = false, defaultValue = "ASC")String sortDir
            ){
        List<FlightDto> dtos = flightService.getAllFlights(pageNo, pageSize, sortBy, sortDir);
        return dtos;
    }
    //http://localhost:8080/api/flight/search?departureCity={departureCity}&arrivalCity={arrivalCity}&departureDate={departureDate}&pageNo={pageNo}&pageSize={pageSize}&sortBy={sortBy}&sortDir={sortDir}
    @GetMapping("/search")
    public List<FlightDto> searchFlights(
            @RequestParam("departureCity") String departureCity,
            @RequestParam("arrivalCity") String arrivalCity,
            @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "ASC") String sortDir
    ) {
        return flightService.searchFlights(departureCity, arrivalCity, departureDate, pageNo, pageSize, sortBy, sortDir);
    }
    //http://localhost:8080/api/flight/{flightNumber}
    @GetMapping("/{flightNumber}")
    public ResponseEntity<FlightDto> findByFlightNumber(@PathVariable("flightNumber") String flightNumber){
        FlightDto dto = flightService.findByFlightNumber(flightNumber);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FlightDto> updateFlight(@RequestBody FlightDto flightDto, @PathVariable("id") long id){
        FlightDto dto = flightService.updateFlight(flightDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping("/{flightId}")
    public ResponseEntity<FlightDto> updateFlightByFlightNumber(@RequestBody FlightDto flightDto, @PathVariable("flightNumber") String flightNumber){
        FlightDto dto = flightService.updateFlightByFlightNumber(flightDto, flightNumber);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlightById(@PathVariable("id") long id){
        flightService.deleteFlightById(id);
        return new ResponseEntity<>("flight is deleted", HttpStatus.OK);
    }

}
