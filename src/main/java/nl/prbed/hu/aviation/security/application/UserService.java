package nl.prbed.hu.aviation.security.application;

import nl.prbed.hu.aviation.security.application.exception.UserAlreadyExistsException;
import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import nl.prbed.hu.aviation.management.data.user.EmployeeEntity;
import nl.prbed.hu.aviation.security.data.SpringUserRepository;
import nl.prbed.hu.aviation.security.data.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

/**
 *  Implements UserDetailsService in order to make it usable
 *  as login/registration service for Spring.
 *  (see AuthenticationFilter)
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    private final SpringUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(SpringUserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerCustomer(
            String username,
            String password,
            String firstName,
            String lastName,
            String nationality,
            LocalDate birthDate,
            String email,
            String phoneNumber
    ) {
        this.checkIfUserExists(username);
        var encodedPassword = this.passwordEncoder.encode(password);
        var customer = new CustomerEntity(username, encodedPassword, firstName, lastName, nationality, birthDate, email, phoneNumber);
        this.save(customer);
    }

    public void registerEmployee(String username, String password, String firstName, String lastName) {
        this.checkIfUserExists(username);
        var encodedPassword = this.passwordEncoder.encode(password);
        var employee = new EmployeeEntity(username, encodedPassword, firstName, lastName);
        this.save(employee);
    }

    private void checkIfUserExists(String username) {
        var user = this.userRepository.findByUsername(username);
        if (user.isPresent())
            throw new UserAlreadyExistsException(username);
    }

    public CustomerEntity findCustomerById(Long id) {
        return (CustomerEntity) this.userRepository.findByIdAndCustomer(id)
                .orElseThrow(() -> new NullPointerException(id.toString()));
    }

    private void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
