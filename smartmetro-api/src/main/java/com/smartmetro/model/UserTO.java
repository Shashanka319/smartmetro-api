package com.smartmetro.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class UserTO {
    private Integer userId;

    private String userName;

    private String email;

    private Long phone;

    private String password;

    private String role;

    private LocalDate date;
}
