package nl.prbed.hu.aviation.management.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {
    // TODO: Do we really need this class, will it have any relations to other classes?
}
