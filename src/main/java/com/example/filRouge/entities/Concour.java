package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;

@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Concour {
    @OneToMany(mappedBy = "concour",cascade = CascadeType.REMOVE)
    private List<Module> modules;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private Integer anneeConcours;
    @Temporal(DATE)
    private LocalDate dateConcoursEcrit;
    @Temporal(DATE)
    private LocalDate dateConcoursOral;
    private Integer nbreplace;
    private Integer nbreplaceConcoursEcrit;
    private Integer nbreplaceConcoursOral;

    @ManyToOne
    private Filiere filiere;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;
}
