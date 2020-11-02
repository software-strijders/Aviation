package nl.prbed.hu.aviation.management.data.booking;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.flight.FlightEntity;
import nl.prbed.hu.aviation.management.data.user.CustomerEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double price;
    private Boolean confirmed;

    @ManyToOne
    private CustomerEntity customer;

    @OneToOne
    private FlightEntity flight;

    @OneToMany
    private List<PassengerEntity> passengers;

    public BookingEntity() {}
    public BookingEntity(
            Double price,
            CustomerEntity customer,
            FlightEntity flight,
            List<PassengerEntity> passengers
    ) {
        this.price = price;
        this.customer = customer;
        this.flight = flight;
        this.passengers = passengers;
    }
}
