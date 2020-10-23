package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.user.EmployeeEntity;
import nl.prbed.hu.aviation.management.domain.Employee;
import nl.prbed.hu.aviation.management.domain.factory.EmployeeFactory;
import nl.prbed.hu.aviation.security.data.SpringUserRepository;
import nl.prbed.hu.aviation.security.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private static final String ERROR_MSG = "Could not find employee with id '%s'";

    private final SpringUserRepository userRepository;
    private final EmployeeFactory employeeFactory;

    public void deleteEmployee(String username) {
        var employee = this.map(this.userRepository.findByUsernameAndEmployee(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, username))));
        this.userRepository.delete(employee);
    }

    public Employee findByUsername(String username) {
        return this.employeeFactory.from(this.map(this.userRepository.findByUsernameAndEmployee(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, username)))));
    }

    public List<Employee> findAll() {
        var employeeEntities = new ArrayList<EmployeeEntity>();
        this.userRepository.findAllEmployees().forEach( user -> employeeEntities.add(map(user)));
        return this.employeeFactory.from(employeeEntities);
    }

    private EmployeeEntity map(User user) {
        return (EmployeeEntity) user;
    }
}
