package com.example.graduationthesis.data.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    String pw;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String role;

    @OneToMany(mappedBy = "user")
    List<Lecture> lectures;
}
