package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
    @ManyToOne
    private Departement departement;
}
