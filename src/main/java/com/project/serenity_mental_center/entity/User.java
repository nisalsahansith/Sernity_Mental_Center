package com.project.serenity_mental_center.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table
public class User {
    @Id
    private String id;

    private String name;

    private String username;
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false,unique = true)
    private String email;
}
