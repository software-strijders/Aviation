package nl.prbed.hu.aviation.management.data.user;

import lombok.Getter;
import lombok.Setter;
import nl.prbed.hu.aviation.management.data.booking.BookingEntity;
import nl.prbed.hu.aviation.security.data.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends User {
    @Column
    private String nationality;
    private LocalDate birthDate;
    private String email;
    private int phoneNumber;

    @OneToMany
    private List<BookingEntity> bookings;

    public CustomerEntity() {}
    public CustomerEntity(
            String username,
            String password,
            String firstName,
            String lastName,
            String nationality,
            LocalDate birthDate,
            String email,
            int phoneNumber
    ) {
        super(username, password, firstName, lastName);
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addBooking(BookingEntity entity) {
        this.bookings.add(entity);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }
}
