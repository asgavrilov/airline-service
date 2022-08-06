package h.assignment.taskh.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

}
