package com.example.filRouge.service.noteModule;

import com.example.filRouge.entities.NoteModule;
import com.example.filRouge.entities.Result;

import java.util.List;

public interface NoteModuleService {
    public NoteModule findByResultAndRefModuleConcours(Result result, String reference);

    public NoteModule saveNoteModule(NoteModule noteModule);

    public NoteModule updateNoteModule(NoteModule noteModule);


}
