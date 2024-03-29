package com.example.filRouge.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Concour> concourList;
}
