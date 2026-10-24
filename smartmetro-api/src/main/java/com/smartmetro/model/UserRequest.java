package com.smartmetro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;
@ToString
@Getter
@Setter
public class UserRequest {
    private Integer userId;

    private String userName;

    private String email;

    private Long phone;

    private String password;

    private String role;

    private LocalDate date;

    Set<MetroCardTO> metroCards;
}
