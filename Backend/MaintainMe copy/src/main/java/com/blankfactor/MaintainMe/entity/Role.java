package com.blankfactor.MaintainMe.entity;

import com.blankfactor.MaintainMe.enums.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "user_role", nullable = false)
    private String userRole;


    @OneToMany(mappedBy = "role", orphanRemoval = true)
    private List<UserRoleBuilding> userRoleBuildings = new ArrayList<>();


}