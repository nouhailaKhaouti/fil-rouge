package com.example.filRouge.controller.vm.result;

import com.example.filRouge.controller.vm.choix.choixWithConcoursAndInscription;
import lombok.Data;

@Data
public class updateResult {

    private choixWithConcoursAndInscription choix;
    private boolean preselectione;
    private boolean retenueOral;
    private boolean admis;
}
