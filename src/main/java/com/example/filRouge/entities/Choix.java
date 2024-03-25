package com.example.filRouge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Choix {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer numChoix;
    @ManyToOne
    @JsonBackReference
    private Inscription inscription;
    @ManyToOne
    @JsonBackReference
    private Concour concour;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Result result;
}
