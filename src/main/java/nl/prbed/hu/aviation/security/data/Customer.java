package nl.prbed.hu.aviation.security.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends User {
    @Column
    private String nationality;
    private LocalDate birthDate;
    private String email;
    private int phoneNumber;

    public Customer() {}
    public Customer(
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

    // TODO: Add this:
    // private List<Booking> bookings;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }
}
