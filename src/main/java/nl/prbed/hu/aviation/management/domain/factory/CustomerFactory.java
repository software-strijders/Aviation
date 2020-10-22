package nl.prbed.hu.aviation.management.domain.factory;

import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import nl.prbed.hu.aviation.management.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerFactory {
    public Customer from(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNationality(),
                entity.getBirthDate(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                // TODO: Add bookings to Customer
                null
        );
    }

    public List<Customer> from(List<CustomerEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
