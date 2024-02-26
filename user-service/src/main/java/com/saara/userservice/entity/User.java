package com.saara.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String fistName;
    private String lastName;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Roles roles;

}
