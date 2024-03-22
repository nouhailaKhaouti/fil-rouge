package com.example.filRouge.service.noteModule;


import com.example.filRouge.Repository.NoteModuleRepository;
import com.example.filRouge.entities.NoteModule;
import com.example.filRouge.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteModuleServiceImpl implements NoteModuleService {

    final private NoteModuleRepository noteModuleRepository;
    @Override
    public List<NoteModule> findByConcourReference(String reference) {
        return null;
    }

    @Override
    public NoteModule findByReference(String reference) {
/*        Optional<NoteModule> noteModule=noteModuleRepository.findByReference(reference);
        if(noteModule.isEmpty()){
            throw new NotFoundException();
        }*/
        return null;
    }

    @Override
    public NoteModule findById(Long id) {
        Optional<NoteModule> noteModule=noteModuleRepository.findById(id);
        if(noteModule.isEmpty()){
            throw new NotFoundException();
        }
        return noteModule.get();
    }

    @Override
    public NoteModule saveNoteModule(NoteModule noteModule) {
/*        Optional<NoteModule> OptionalNoteModule=noteModuleRepository.findByReference(noteModule.getReference());
        if(OptionalNoteModule.isPresent()){
            throw new AlreadyExistException();
        }*/
        return noteModuleRepository.save(noteModule);
    }

    @Override
    public NoteModule updateNoteModule(NoteModule noteModule) {
/*        NoteModule noteModule1=findByReference(noteModule.getReference());
        noteModule1.setCoefNoteModule(noteModule.getCoefNoteModule());*/
        return null;
    }

    @Override
    public void DeleteNoteModule(NoteModule noteModule) {
        noteModuleRepository.delete(null);
    }

    @Override
    public NoteModule DeleteNoteModuleById(Long id) {
        return null;
    }

}
