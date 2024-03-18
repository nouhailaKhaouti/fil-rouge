package com.example.filRouge.controller.vm.concour;

import com.example.filRouge.controller.vm.filiere.filiereLabel;
import com.example.filRouge.controller.vm.module.ModuleWithOutConcour;
import com.example.filRouge.entities.Niveau;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data

public class ResponseConcour {
    private List<ModuleWithOutConcour> modules;
    private String reference;
    private int anneeConcours;
    private LocalDate dateConcoursEcrit;
    private LocalDate dateConcoursOral;
    private int nbreplace;
    private int nbreplaceConcoursEcrit;
    private int nbreplaceConcoursOral;
    private filiereLabel filiere;
    private Niveau niveau;
}
