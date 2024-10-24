package com.wsu.workorderproservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "application")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdoptionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne
    // @JoinColumn(name = "adopter_id")
    // private adopter adopter;

    // @ManyToOne
    // @JoinColumn(name = "pet_id")
    // private pet pet;

    // @ManyToOne
    // @JoinColumn(name = "staff_id")
    // private staff staff;

    private String status;

   
}
