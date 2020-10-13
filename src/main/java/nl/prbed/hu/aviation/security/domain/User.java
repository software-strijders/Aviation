package nl.prbed.hu.aviation.security.domain;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String firstName;
    private String surName;
}
