package nl.prbed.hu.aviation.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Fleet {
    private List<Aircraft> aircrafts;
    private Airport airport;
}
