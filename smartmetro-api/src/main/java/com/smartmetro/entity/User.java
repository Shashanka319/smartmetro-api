package com.smartmetro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq",sequenceName = "users_seq",allocationSize=1)
    @Column(name="USER_ID ")
    private Integer userId;
    @Column(name="FULL_NAME")
    private String userName;
    @Column(name="EMAIL")
    private String email;
    @Column(name="PHONE_NUMBER")
    private Long phone;
    @Column(name = "PASSWORD_HASH")
    private String password;
    @Column(name = "CREATED_AT")
    private LocalDate date;
    @Column(name = "ROLE")
    private String role;

    @OneToMany(mappedBy = "user1")
    private Set<MetroCard> metroCards;

}
