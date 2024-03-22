package com.example.filRouge.controller.vm.result;

import com.example.filRouge.controller.vm.choix.choixWithConcoursAndInscription;
import com.example.filRouge.controller.vm.noteModule.NoteModuleRequest;
import lombok.Data;

import java.util.List;

@Data
public class resultRequestWithOralNote {
    private List<NoteModuleRequest> noteModules;
    private choixWithConcoursAndInscription choix;
    private Double noteOral;

}
