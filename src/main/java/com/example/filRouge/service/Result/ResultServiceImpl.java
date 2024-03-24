package com.example.filRouge.service.Result;


import com.example.filRouge.Repository.ResultRepository;
import com.example.filRouge.entities.NoteModule;
import com.example.filRouge.entities.Result;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.noteModule.NoteModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    final private ResultRepository resultRepository;
    final private NoteModuleService noteModuleService;
    @Override
    public List<Result> findByConcourReference(String reference) {
        return null;
    }

    @Override
    public Result findByReference(String reference) {
/*        Optional<Result> result=resultRepository.findByReference(reference);
        if(result.isEmpty()){
            throw new NotFoundException();
        }*/
        return null;
    }

    @Override
    public Result findById(Long id) {
        Optional<Result> result=resultRepository.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException();
        }
        return result.get();
    }

    @Override
    public Result saveResult(Result result) {
        if(resultRepository.findByChoix(result.getChoix())!=null){
            throw  new AlreadyExistException();
        }
        return resultRepository.save(result);
    }

    @Override
    public Result updateResult(Result result) {

        return null;
    }
    @Override
    public Result updateResultOral(Result result) {
        Result result1=searchResult(result);
        result1.setNoteOral(result.getNoteOral());
        return resultRepository.save(result1);
    }

    @Override
    public Result preselectionResult(Result result) {
        Result result1=searchResult(result);
        result1.setPreselectione(true);
        return resultRepository.save(result1);
    }

    @Override
    public Result writingResult(Result result) {
        Result result1=searchResult(result);
        if(result1.isPreselectione()){
            result1.setRetenueOral(true);
            return resultRepository.save(result1);
        }
        throw new CustomException("this student can't pass the writing exam as he's not in the preselection list", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Result admisResult(Result result) {
        Result result1=searchResult(result);
        if(result1.isPreselectione() && result.isRetenueOral()){
            result1.setAdmis(true);
            return resultRepository.save(result1);
        }
        throw new CustomException("this student can't be in the final list as he did not pass the oral exam ", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Result addModuleNote(Result result) {
         Result result1=searchResult(result);
        final Double[] resultWriting = {0.0};
        List<NoteModule> noteModules=new ArrayList<>();
        result.getNoteModules().forEach(noteModule -> {
            result.getChoix().getConcour().getModules().forEach(module -> {
                if(module.getReference().equals(noteModule.getRefModuleConcours())) {
                    NoteModule noteModule1=noteModuleService.findByResultAndRefModuleConcours(result1,noteModule.getRefModuleConcours());
                    resultWriting[0] += noteModule.getNote() * module.getCoefModule();
                    if(noteModule1==null) {
                        noteModule.setResult(result1);
                        noteModules.add(noteModuleService.saveNoteModule(noteModule));
                    }else{
                        noteModule1.setNote(noteModule.getNote());
                        noteModuleService.saveNoteModule(noteModule1);
                    }
                }
            });
        });
        result1.setNoteModules(noteModules);
        result1.setNoteEcrit(resultWriting[0]/result.getNoteModules().size());
        return resultRepository.save(result1);
    }

    @Override
    public void DeleteResult(Result result) {

        resultRepository.delete(searchResult(result));
    }

    public Result searchResult(Result result){
        Result result1=resultRepository.findByChoix(result.getChoix());
        if(result1==null){
            throw new NotFoundException();
        }
        return result1;
    }

}
