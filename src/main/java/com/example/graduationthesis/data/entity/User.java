package com.example.graduationthesis.data.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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

    @Column
    String pw;

    @Column
    String name;

    @Column
    String role;
}
