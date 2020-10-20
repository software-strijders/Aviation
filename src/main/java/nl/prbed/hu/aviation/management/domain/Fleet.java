package nl.prbed.hu.aviation.management.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Fleet {
    private List<Aircraft> aircrafts;
    private Airport airport;
}
