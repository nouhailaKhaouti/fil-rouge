package com.example.filRouge.controller.vm.choix;

import com.example.filRouge.controller.vm.concour.concourWithReferenceOnly;
import com.example.filRouge.controller.vm.inscription.inscriptionWithCinAndNiveau;
import lombok.Data;

@Data
public class choixWithConcoursAndInscription {
    private concourWithReferenceOnly concour;
    private inscriptionWithCinAndNiveau inscription;
}
