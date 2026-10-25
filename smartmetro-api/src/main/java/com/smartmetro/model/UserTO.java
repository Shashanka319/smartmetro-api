package com.smartmetro.model;

import com.smartmetro.entity.MetroCard;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class UserTO {
    private Integer userId;

    private String userName;

    private String email;

    private Long phone;

    private String password;

    private String role;

    private LocalDate date;

    Set<MetroCardTO> metroCards;

    public UserTO() {

    }
}
