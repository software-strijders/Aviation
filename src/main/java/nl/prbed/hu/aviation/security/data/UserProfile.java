package nl.prbed.hu.aviation.security.data;

/**
 * This is a data model.
 *
 * It is similar to a domain model, but is
 * intended for storage purposes. It does not
 * contain a lot of business logic.
 */
public class UserProfile {
    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String role;

    public UserProfile(Long id, String username, String firstName, String lastName, String role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }
}
