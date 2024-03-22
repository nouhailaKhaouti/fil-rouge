package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany( cascade = CascadeType.REMOVE, mappedBy = "result")
    private List<NoteModule> noteModules;
    private boolean preselectione;
    private boolean retenueOral;
    private boolean admis;
    private Double notePreselection;
    private Double noteEcrit;
    private Double noteOral;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Choix choix;

}
