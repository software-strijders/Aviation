package nl.prbed.hu.aviation.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class Aircraft {
    private String code;
    private Type type;
    private Flight current;
    private List<Flight> past;
    private Fleet fleet;
}
