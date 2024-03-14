package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
    private Date dateConcoursEcrit;
    @Temporal(DATE)
    private Date dateConcoursOral;
    private int nbreplace;
    private int nbreplaceConcoursEcrit;
    private int nbreplaceConcoursOral;

    @ManyToOne
    private Filiere filiere;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;
}
