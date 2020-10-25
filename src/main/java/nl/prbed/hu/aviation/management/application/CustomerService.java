package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import nl.prbed.hu.aviation.management.domain.Customer;
import nl.prbed.hu.aviation.management.domain.factory.CustomerFactory;
import nl.prbed.hu.aviation.security.data.SpringUserRepository;
import nl.prbed.hu.aviation.security.data.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private static final String ERROR_MSG = "Could not find customer with id '%s'";

    private final SpringUserRepository userRepository;
    private final CustomerFactory factory;

    public void deleteCustomer(String username) {
        this.userRepository.delete(this.findByCustomerUsername(username));
    }

    public List<Customer> findAllCustomers() {
        var entities = this.userRepository.findAllCustomers().stream()
                .map(this::map).collect(Collectors.toList());
        return this.factory.from(entities);
    }

    public Customer findById(Long id) {
        var entity = this.userRepository.findByIdAndCustomer(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, id)));

        return this.factory.from(this.map(entity));
    }

    public Customer update(
            String username,
            String firstName,
            String lastName,
            String nationality,
            LocalDate birthDate,
            String email,
            int phoneNumber
    ) {
        var customer = this.findByCustomerUsername(username);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setNationality(nationality);
        customer.setBirthDate(birthDate);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        return this.factory.from(this.userRepository.save(customer));
    }

    private CustomerEntity findByCustomerUsername(String username) {
        return this.map(this.userRepository.findByUsernameAndCustomer(username)
                        .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, username))));
    }

    private CustomerEntity map(User user) {
        return (CustomerEntity) user;
    }
}
