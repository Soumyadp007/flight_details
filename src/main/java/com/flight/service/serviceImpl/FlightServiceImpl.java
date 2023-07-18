package com.flight.service.serviceImpl;

import com.flight.entity.Flight;
import com.flight.exception.ResourceNotFoundException;
import com.flight.payload.FlightDto;
import com.flight.repository.FlightRepository;
import com.flight.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightRepository flightRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public FlightDto createFlight(FlightDto flightDto) {
        Flight flight = mapToEntity(flightDto);
        Flight save = flightRepo.save(flight);
        return mapToDto(save);
    }

    @Override
    public List<FlightDto> getAllFlights(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        Page<Flight> pageAll = flightRepo.findAll(pageable);
        List<Flight> all = pageAll.getContent();
        return all.stream().map(flight -> mapToDto(flight)).collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> searchFlights(String departureCity, String arrivalCity, LocalDate departureDate, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Flight> findFlight = flightRepo.findByDepartureCityAndArrivalCityAndDepartureDate(pageable, departureCity, arrivalCity, departureDate);
        List<Flight> all = findFlight.getContent();
        return all.stream().map(flight -> mapToDto(flight)).collect(Collectors.toList());
    }

    @Override
    public FlightDto updateFlight(FlightDto flightDto, long id) {
        Flight flightId = flightRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("flight not found with id:" + id)
        );
        Flight flight = mapToEntity(flightDto);
        flightId.setId(id);
        Flight save = flightRepo.save(flight);
        return mapToDto(save);

    }

    @Override
    public FlightDto updateFlightByFlightNumber(FlightDto flightDto, String flightNumber) {
        Flight byFlightId = flightRepo.findByFlightNumber(flightNumber);
        Flight flight = mapToEntity(flightDto);
        byFlightId.setFlightNumber(flightNumber);
        Flight save = flightRepo.save(flight);
        return mapToDto(save);
    }

    @Override
    public void deleteFlightById(long id) {
        Flight flightId = flightRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("flight not found with id:" + id)
        );
        flightRepo.deleteById(id);
    }

    @Override
    public FlightDto findByFlightNumber(String flightNumber) {
        Flight byFlightNumber = flightRepo.findByFlightNumber(flightNumber);
        if (byFlightNumber == null) {
            throw new ResourceNotFoundException("Flight not found with flight number: " + flightNumber);
        }
        return mapToDto(byFlightNumber);
    }



    Flight mapToEntity(FlightDto flightDto){
        return modelMapper.map(flightDto, Flight.class);
    }
    FlightDto mapToDto(Flight flight){
        return modelMapper.map(flight, FlightDto.class);
    }
}
