package com.example.filRouge.service.noteModule;


import com.example.filRouge.Repository.NoteModuleRepository;
import com.example.filRouge.entities.NoteModule;
import com.example.filRouge.entities.Result;
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
    public NoteModule findByResultAndRefModuleConcours(Result result, String reference) {
        return noteModuleRepository.findByResultAndRefModuleConcours(result,reference);
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

}
