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
    @Column(name="user_id")
    private Integer userId;
    @Column(name="name")
    private String userName;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private Long phone;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "created_at")
    private LocalDate date;

    @OneToMany(mappedBy = "user1")
    private Set<MetroCard> metroCards;



}
