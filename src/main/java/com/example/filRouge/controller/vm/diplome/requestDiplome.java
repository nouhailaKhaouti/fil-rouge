package com.example.filRouge.controller.vm.diplome;

import com.example.filRouge.controller.vm.semestre.requestSemestre;
import com.example.filRouge.entities.DiplomeName;
import lombok.Data;

import java.util.List;

@Data
public class requestDiplome {
    private DiplomeName refDiplome;
    private Integer anneeObtention;
    private String mention;
    private Double note;
    private List<requestSemestre> semestres;
}
