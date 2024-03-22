package com.example.filRouge.service.noteModule;

import com.example.filRouge.entities.NoteModule;

import java.util.List;

public interface NoteModuleService {
    public List<NoteModule> findByConcourReference(String reference);

    public NoteModule findByReference(String reference);

    public NoteModule findById(Long id);

    public NoteModule saveNoteModule(NoteModule noteModule);

    public NoteModule updateNoteModule(NoteModule noteModule);

    public void DeleteNoteModule(NoteModule noteModule);

    public NoteModule DeleteNoteModuleById(Long id);

}
