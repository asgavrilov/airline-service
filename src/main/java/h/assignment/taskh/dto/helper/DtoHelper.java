package h.assignment.taskh.dto.helper;

import h.assignment.taskh.dto.AirlineDto;
import h.assignment.taskh.dto.DestinationDto;
import h.assignment.taskh.dto.FlightDto;
import h.assignment.taskh.entity.Airline;
import h.assignment.taskh.entity.Destination;
import h.assignment.taskh.entity.Flight;

public class DtoHelper {

    private DtoHelper() {
    }

    public static AirlineDto entityToDto(Airline airline) {
        return AirlineDto.builder()
                .id(airline.getId())
                .airlineName(airline.getAirlineName())
                .address(airline.getAddress())
                .email(airline.getEmail())
                .managerName(airline.getManagerName())
                .build();
    }

    public static DestinationDto entityToDto(Destination destination) {
        return DestinationDto.builder()
                .airportId(destination.getAirportId())
                .country(destination.getCountry())
                .cityName(destination.getCityName())
                .build();

    }

    public static FlightDto entityToDto(Flight flight) {
        return FlightDto.builder()
                .flightNumber(flight.getFlightNumber())
                .destinationFrom(entityToDto(flight.getDestinationFrom()))
                .destinationTo(entityToDto(flight.getDestinationTo()))
                .connectionFlights(flight.getConnectionFlights().stream().map(DtoHelper::entityToDto).toList())
                .airline(entityToDto(flight.getAirline()))
                .build();
    }

    public static Flight dtoToEntity(FlightDto flightDto) {
        return Flight.builder()
                .flightNumber(flightDto.getFlightNumber())
                .destinationFrom(dtoToEntity(flightDto.getDestinationFrom()))
                .destinationTo(dtoToEntity(flightDto.getDestinationTo()))
                .connectionFlights(flightDto.getConnectionFlights().stream().map(DtoHelper::dtoToEntity).toList())
                .airline(dtoToEntity(flightDto.getAirline()))
                .build();
    }

    public static Airline dtoToEntity(AirlineDto airlineDto) {
        return Airline.builder()
                .id(airlineDto.getId())
                .airlineName(airlineDto.getAirlineName())
                .address(airlineDto.getAddress())
                .email(airlineDto.getEmail())
                .managerName(airlineDto.getManagerName())
                .build();
    }

    public static Destination dtoToEntity(DestinationDto destinationDto) {
        return Destination.builder()
                .airportId(destinationDto.getAirportId())
                .country(destinationDto.getCountry())
                .cityName(destinationDto.getCityName())
                .build();
    }
}
