package nl.prbed.hu.aviation.management.domain.factory;

import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import nl.prbed.hu.aviation.management.domain.Customer;
import org.springframework.stereotype.Component;

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
}
