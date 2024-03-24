package com.example.filRouge.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany( cascade = CascadeType.REMOVE, mappedBy = "result")
    @JsonManagedReference
    private List<NoteModule> noteModules;
    private String resultRef;
    private boolean preselectione=false;
    private boolean retenueOral=false;
    private boolean admis=false;
    private Double notePreselection;
    private Double noteEcrit;
    private Double noteOral;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JsonBackReference
    private Choix choix;
}
