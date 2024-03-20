package com.example.filRouge.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Semestre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Diplome diplome;
    private String refSemestre;
    private Double note;
}
