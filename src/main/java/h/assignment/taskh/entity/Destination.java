package h.assignment.taskh.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "destinations")
public class Destination {
    @Id
    @Column(name = "airport_id", unique = true)
    private String airportId;

    @Column(name = "country")
    private String country;

    @Column(name = "city_name")
    private String cityName;

    public void setAirportId(String airportId) {
        this.airportId = airportId.toLowerCase(Locale.ROOT);
    }

    public void setCountry(String country) {
        this.country = country.toLowerCase(Locale.ROOT);
    }

    public void setCityName(String cityName) {
        this.cityName = cityName.toLowerCase(Locale.ROOT);
    }
}
