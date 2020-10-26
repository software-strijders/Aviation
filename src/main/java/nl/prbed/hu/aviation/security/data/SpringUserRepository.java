package nl.prbed.hu.aviation.security.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SpringUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("FROM User u WHERE TYPE(u) = CustomerEntity")
    List<User> findAllCustomers();

    @Query("FROM User u WHERE TYPE(u) = EmployeeEntity")
    List<User> findAllEmployees();

    @Query("FROM User u WHERE TYPE(u) = CustomerEntity AND u.id = :id")
    Optional<User> findByIdAndCustomer(@Param("id") Long id);

    @Query("FROM User u WHERE TYPE(u) = EmployeeEntity AND u.id = :id")
    Optional<User> findByIdAndEmployee(@Param("id") Long id);

    @Query("FROM User u WHERE TYPE(u) = CustomerEntity AND u.username = :username")
    Optional<User> findByUsernameAndCustomer(@Param("username") String username);

    @Query("FROM User u WHERE TYPE(u) = EmployeeEntity AND u.username = :username")
    Optional<User> findByUsernameAndEmployee(@Param("username") String username);
}
