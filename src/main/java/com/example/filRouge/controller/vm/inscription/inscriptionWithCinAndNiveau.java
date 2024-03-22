package com.example.filRouge.controller.vm.inscription;

import com.example.filRouge.entities.Niveau;
import lombok.Data;

@Data
public class inscriptionWithCinAndNiveau {
    private Niveau niveau;
    private String cin;
}
