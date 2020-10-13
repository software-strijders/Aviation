package nl.prbed.hu.aviation.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long userId;
    private String firstName;
    private String surName;
}
