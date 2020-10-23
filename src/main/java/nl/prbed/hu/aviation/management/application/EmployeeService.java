package nl.prbed.hu.aviation.management.application;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.management.application.exception.EntityNotFoundException;
import nl.prbed.hu.aviation.management.data.user.CustomerEntity;
import nl.prbed.hu.aviation.security.data.Employee;
import nl.prbed.hu.aviation.security.data.SpringUserRepository;
import nl.prbed.hu.aviation.security.data.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private static final String ERROR_MSG = "Could not find employee with id '%s'";

    private final SpringUserRepository userRepository;

    public void deleteEmployee(String username) {
        var employee = this.map(this.userRepository.findByUsernameAndEmployee(username)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ERROR_MSG, username))));
        this.userRepository.delete(employee);
    }

    private Employee map(User user) {
        return (Employee) user;
    }
}
