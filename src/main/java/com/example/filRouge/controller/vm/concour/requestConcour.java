package com.example.filRouge.controller.vm.concour;

import com.example.filRouge.controller.vm.filiere.filiereLabel;
import com.example.filRouge.controller.vm.module.ModuleWithOutConcour;
import com.example.filRouge.entities.Niveau;
import lombok.Data;

import java.sql.Date;
import java.util.List;


@Data
public class requestConcour {
    private List<ModuleWithOutConcour> modules;
    private String reference;
    private int anneeConcours;
    private Date dateConcoursEcrit;
    private Date dateConcoursOral;
    private int nbreplace;
    private int nbreplaceConcoursEcrit;
    private int nbreplaceConcoursOral;
    private filiereLabel filiere;
    private Niveau niveau;
}
