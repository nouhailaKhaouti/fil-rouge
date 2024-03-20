package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.TemporalType.DATE;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Inscription {
    @OneToMany(mappedBy = "inscription", cascade = CascadeType.REMOVE)
    private List<Diplome> diplomes;

    @OneToMany(mappedBy = "inscription", cascade = CascadeType.REMOVE)
    private List<Choix> choixs;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cne;
    private String nom;
    private String prenom;
    private String cin;
    private Long tel;
    private String email;
    private String niveau;
    private String AdressePersonnelle;
    private String statue;
    @Temporal(DATE)
    private LocalDate dateNaissance;

}
