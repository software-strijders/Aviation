package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.user.EmployeeEntity;
import nl.prbed.hu.aviation.management.domain.Employee;
import nl.prbed.hu.aviation.management.domain.factory.EmployeeFactory;
import nl.prbed.hu.aviation.security.data.SpringUserRepository;
import nl.prbed.hu.aviation.security.data.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private static final String ERROR_MSG = "Could not find employee with id: '%s'";

    private final SpringUserRepository repository;

    private final EmployeeFactory factory;

    public void deleteByUsername(String username) {
        var entity = this.map(this.repository.findByUsernameAndEmployee(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, username))));
        this.repository.delete(entity);
    }

    public Employee findByUsername(String username) {
        var entity = this.map(this.repository.findByUsernameAndEmployee(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, username))));
        return this.factory.from(entity);
    }

    public List<Employee> findAll() {
        var entities = this.repository.findAllEmployees().stream()
                .map(this::map).collect(Collectors.toList());
        return this.factory.from(entities);
    }

    public Employee update(String username, String firstName, String lastName) {
        var entity = this.findEntityByUsername(username);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        return this.factory.from(this.repository.save(entity));
    }

    public EmployeeEntity findEntityByUsername(String username) {
        return this.map(this.repository.findByUsernameAndEmployee(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, username))));
    }

    private EmployeeEntity map(User user) {
        return (EmployeeEntity) user;
    }
}
