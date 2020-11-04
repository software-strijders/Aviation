package nl.prbed.hu.aviation.management.domain.user.factory;

import nl.prbed.hu.aviation.management.data.user.EmployeeEntity;
import nl.prbed.hu.aviation.management.domain.user.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeFactory {
    public Employee from(EmployeeEntity entity) {
        return new Employee(
                entity.getUsername(),
                entity.getFirstName(),
                entity.getLastName()
        );
    }

    public List<Employee> from(List<EmployeeEntity> entities) {
        return entities.stream().map(this::from).collect(Collectors.toList());
    }
}
