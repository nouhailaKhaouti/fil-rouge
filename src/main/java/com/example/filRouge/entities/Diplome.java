package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private DiplomeName refDiplome;
    private Integer anneeObtention;
    private String mention;
    private Double note;

    @ManyToOne
    private Inscription inscription;

    @OneToMany(mappedBy = "diplome")
    private List<Semestre> semestres;

}
