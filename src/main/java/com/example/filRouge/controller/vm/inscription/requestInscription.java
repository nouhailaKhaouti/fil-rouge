package com.example.filRouge.controller.vm.inscription;

import com.example.filRouge.controller.vm.choix.requestChoix;
import com.example.filRouge.controller.vm.diplome.requestDiplome;
import com.example.filRouge.entities.Niveau;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class requestInscription {
    private List<requestDiplome> diplomes;

    private List<requestChoix> choixs;

    private String cne;
    private String nom;
    private String prenom;
    private String cin;
    private String tel;
    private String email;
    private Niveau niveau;
    private String AdressePersonnelle;
    private LocalDate dateNaissance;
}
