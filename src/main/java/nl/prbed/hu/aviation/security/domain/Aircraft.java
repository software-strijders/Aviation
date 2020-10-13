package nl.prbed.hu.aviation.security.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class Aircraft {
    private String id;
    private Type type;
    private Flight current;
    private List<Flight> past;
    private Fleet fleet;
}
