package nl.prbed.hu.aviation.security.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

/**
 * This is a data model.
 *
 * It is similar to a domain model, but is
 * intended for storage purposes. It does not
 * contain a lot of business logic.
 *
 * It implements UserDetails in order to make it usable
 * as login/registration model for Spring.
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public User(){}
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
