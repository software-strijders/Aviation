package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import nl.prbed.hu.aviation.management.domain.Customer;
import nl.prbed.hu.aviation.management.domain.factory.CustomerFactory;
import nl.prbed.hu.aviation.security.data.SpringUserRepository;
import nl.prbed.hu.aviation.security.data.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private static final String ERROR_MSG = "Could not find customer with id '%s'";

    private final SpringUserRepository userRepository;
    private final CustomerFactory factory;

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

    public void deleteCustomer(String username) {
        var customer = this.map(this.userRepository.findByUsernameAndCustomer(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, username))));
        this.userRepository.delete(customer);
    }

    private CustomerEntity map(User user) {
        return (CustomerEntity) user;
    }
}
