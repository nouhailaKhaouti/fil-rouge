package com.example.filRouge.controller.vm.result;

import com.example.filRouge.controller.vm.noteModule.NoteModuleRequest;
import com.example.filRouge.entities.NoteModule;
import lombok.Data;

import java.util.List;

@Data
public class responseResult {
    private List<NoteModuleRequest> noteModules;
    private String resultRef;
    private boolean preselectione=false;
    private boolean retenueOral=false;
    private boolean admis=false;
    private Double notePreselection;
    private Double noteEcrit;
    private Double noteOral;
}
