package nl.prbed.hu.aviation.config;

import lombok.RequiredArgsConstructor;
import nl.prbed.hu.aviation.security.application.UserService;
import nl.prbed.hu.aviation.security.application.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInserter {
    private final Logger logger = LoggerFactory.getLogger(DataInserter.class);
    private final UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void insertData() {
        this.logger.info("Inserting data...");
        // TODO: More data should be inserted here:
        this.insertEmployee();
        this.logger.info("Inserting data complete!");
    }

    private void insertEmployee() {
        // TODO: This should eventually come from a JSON file:
        try {
            this.userService.registerEmployee("admin", "admin", "Em", "Ployee");
        } catch (UserAlreadyExistsException ex) {
            // When loading data from json and inserting it here, we should probably return here. We can assume it
            // doesn't need to further insertions if one of the records already exists.
            this.logger.info("Default employee already inserted, skipping...");
        }
    }
}
