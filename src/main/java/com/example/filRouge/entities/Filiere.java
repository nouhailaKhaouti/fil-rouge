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
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
    @ManyToOne
    private Departement departement;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Concour> concourList;
}
