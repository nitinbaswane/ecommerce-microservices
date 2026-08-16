package com.ecomm.user.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private String phone;
    private String roles;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt=Instant.now();
        updatedAt=Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt=Instant.now();
    }


}
