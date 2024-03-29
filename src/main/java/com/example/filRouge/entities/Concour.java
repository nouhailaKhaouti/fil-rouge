package com.example.filRouge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
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

    @Temporal(DATE)
    private LocalDate createdAt;

    private Integer nbreplace;
    private Integer nbreplaceConcoursEcrit;
    private Integer nbreplaceConcoursOral;

    @ManyToOne
    @JsonBackReference
    private Member createdBY;

    @ManyToOne
    @JsonBackReference
    private Filiere filiere;

    @Enumerated(EnumType.STRING)
    private Niveau niveau;
}
