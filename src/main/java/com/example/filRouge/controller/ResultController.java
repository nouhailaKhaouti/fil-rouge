package com.example.filRouge.controller;



import com.example.filRouge.controller.vm.result.resultRequest;
import com.example.filRouge.controller.vm.result.resultRequestWithOralNote;
import com.example.filRouge.controller.vm.result.updateResult;
import com.example.filRouge.entities.*;
import com.example.filRouge.service.Result.ResultService;
import com.example.filRouge.service.choixService.ChoixService;
import com.example.filRouge.service.inscriptionService.InscriptionService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/result")
@PreAuthorize("hasRole('MANAGER')")
public class ResultController {
    final private ResultService resultService;
    final private ChoixService choixService;
    final private InscriptionService inscriptionService;

    final ModelMapper modelMapper;

/*    @GetMapping("/")
    public ResponseEntity<?> getAllConcours() {
        List<Concour> concours = concourService.findAll();
        return new ResponseEntity<>(concours.stream().map(f->modelMapper.map(f, ResponseConcour.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/{reference}")
    public ResponseEntity<?> getConcours(@PathVariable("reference") String reference) {
        Concour concours = concourService.findByReference(reference);
        return new ResponseEntity<>(modelMapper.map(concours, ResponseConcour.class), HttpStatus.OK);
    }

    @GetMapping("/Filiere/{filiere}/{niveau}")
    public ResponseEntity<?> getAllConcoursByFiliere(@PathVariable("filiere") String filiere,@PathVariable("niveau") String niveau) {
        List<Concour> concours = concourService.findByRefFiliereAndNiveau(Filiere.builder().label(filiere).build(), Niveau.valueOf(niveau));
        return new ResponseEntity<>(concours.stream().map(f->modelMapper.map(f, ResponseConcour.class)).toList(), HttpStatus.OK);
    }*/

    @PutMapping("/")
    public ResponseEntity<?> addModuleResult( @RequestBody() resultRequest resultRequest) {
        Result result=modelMapper.map(resultRequest, Result.class);
        return ResponseEntity.ok(resultService.addModuleNote(searchResult(result)));
    }

    @PutMapping("/preselection")
    public ResponseEntity<?> updatePreselection( @RequestBody() List<updateResult> resultRequest) {
        resultRequest.stream().map(f->modelMapper.map(f, Result.class)).forEach(
                f->{
                    resultService.preselectionResult(searchResult(f));
                }
        );
        return ResponseEntity.ok(resultRequest);
    }

    @PutMapping("/oral")
    public ResponseEntity<?> updateOralNote( @RequestBody() resultRequestWithOralNote resultRequest) {
        Result result=modelMapper.map(resultRequest, Result.class);
        return ResponseEntity.ok(resultService.updateResultOral(searchResult(result)));
    }
    @PutMapping("/writing")
    public ResponseEntity<?> updateWriting(@RequestBody List<updateResult> resultRequest){

        // Perform the result updates
        resultRequest.stream()
                .map(f -> modelMapper.map(f, Result.class))
                .forEach(f -> resultService.writingResult(searchResult(f)));



        // Return the response
        return ResponseEntity.ok(resultRequest);
    }

    @PutMapping("/admis")
    public ResponseEntity<?> updateAdmis( @RequestBody() List<updateResult>  resultRequest) {
        resultRequest.stream().map(f->modelMapper.map(f, Result.class)).forEach(
                f->{
                    resultService.admisResult(searchResult(f));
                }
        );
        return ResponseEntity.ok(resultRequest);
    }

    public Result searchResult(Result result){
         result.setChoix(choixService.findByInscriptionAndConcours(inscriptionService.findByRef(result.getChoix().getInscription()),result.getChoix().getConcour()));
         return result;
    }
}
