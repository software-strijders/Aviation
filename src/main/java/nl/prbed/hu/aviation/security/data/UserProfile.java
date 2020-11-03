package nl.prbed.hu.aviation.security.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This is a data model.
 *
 * It is similar to a domain model, but is
 * intended for storage purposes. It does not
 * contain a lot of business logic.
 */
@Getter
@RequiredArgsConstructor
public class UserProfile {
    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String role;
}
