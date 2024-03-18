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
    @OneToMany(mappedBy = "concour")
    private List<Module> modules;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    private int anneeConcours;
    @Temporal(DATE)
    private LocalDate dateConcoursEcrit;
    @Temporal(DATE)
    private LocalDate dateConcoursOral;
    private int nbreplace;
    private int nbreplaceConcoursEcrit;
    private int nbreplaceConcoursOral;

    @ManyToOne
    private Filiere filiere;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;
}
