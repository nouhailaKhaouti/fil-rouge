package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Filiere> filieres;
}
