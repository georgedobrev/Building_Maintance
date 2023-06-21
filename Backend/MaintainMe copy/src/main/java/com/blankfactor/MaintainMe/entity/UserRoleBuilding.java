package com.blankfactor.MaintainMe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_role_building")
public class UserRoleBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    public UserRoleBuilding(User user, Role role, Building building) {
        this.user = user;
        this.role = role;
        this.building = building;
    }

    public UserRoleBuilding(Long id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }
}
