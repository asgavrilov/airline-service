package h.assignment.taskh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import h.assignment.taskh.entity.Destination;
import lombok.AllArgsConstructor;



@AllArgsConstructor
public class FlightDto {
    private int id;
    private String flightNumber;
    private Destination destinationFrom;
    private Destination destinationTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JsonProperty("flightNumber")
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    @JsonProperty("destinationFrom")
    public Destination getDestinationFrom() {
        return destinationFrom;
    }

    public void setDestinationFrom(Destination destinationFrom) {
        this.destinationFrom = destinationFrom;
    }
    @JsonProperty("destinationTo")
    public Destination getDestinationTo() {
        return destinationTo;
    }

    public void setDestinationTo(Destination destinationTo) {
        this.destinationTo = destinationTo;
    }
}
