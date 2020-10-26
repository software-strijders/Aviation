package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Employee {
    private String username;
    private String firstName;
    private String lastName;
}
