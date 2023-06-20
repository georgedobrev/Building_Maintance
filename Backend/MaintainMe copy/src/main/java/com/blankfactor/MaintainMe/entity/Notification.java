package com.blankfactor.MaintainMe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false)
    @NotBlank(message = "message title cannot be empty.")
    private String messageTitle;

    @Column(name = "information", nullable = false)
    @NotBlank(message = "message information cannot be empty.")
    private String information;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "building_id", nullable = false)
    private Long buildingId;

    @JsonIgnore
    @OneToMany(mappedBy = "notification", orphanRemoval = true)
    @OrderBy("date DESC")
    private List<Comment> comments = new ArrayList<>();

}