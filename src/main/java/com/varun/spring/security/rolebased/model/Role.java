package com.varun.spring.security.rolebased.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.MERGE)  // Added MERGE cascade for consistency
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }
}
