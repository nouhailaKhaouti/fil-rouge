package com.example.filRouge.controller;


import com.example.filRouge.controller.vm.concour.ResponseConcour;
import com.example.filRouge.controller.vm.concour.requestConcour;
import com.example.filRouge.controller.vm.inscription.requestInscription;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.entities.Inscription;
import com.example.filRouge.entities.Niveau;
import com.example.filRouge.service.choixService.ChoixService;
import com.example.filRouge.service.concourService.concourService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/concour")
@PreAuthorize("hasRole('MANAGER')")
public class ChoixController {
    final private concourService concourService;
    final private ChoixService choixService;

    final ModelMapper modelMapper;

    @GetMapping("/{reference}")
    public ResponseEntity<?> getAllChoixByConcour(@PathVariable("reference") String reference) {
        Concour concours = concourService.findByReference(reference);
        List<Inscription> inscriptions = choixService.findByConcours(concours);
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, requestInscription.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/preselection/{reference}")
    public ResponseEntity<?> getAllChoixByConcourPreselection(@PathVariable("reference") String reference) {
        Concour concours = concourService.findByReference(reference);
        List<Inscription> inscriptions = choixService.PeselectionListByConcours(concours);
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, requestInscription.class)).toList(), HttpStatus.OK);
    }
    @GetMapping("/writing/{reference}")
    public ResponseEntity<?> getAllChoixByConcourWriting(@PathVariable("reference") String reference) {
        Concour concours = concourService.findByReference(reference);
        List<Inscription> inscriptions = choixService.WritingListByConcours(concours);
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, requestInscription.class)).toList(), HttpStatus.OK);
    }
    @GetMapping("/admis/{reference}")
    public ResponseEntity<?> getAllChoixByConcourAdmis(@PathVariable("reference") String reference) {
        Concour concours = concourService.findByReference(reference);
        List<Inscription> inscriptions = choixService.AdmisListByConcours(concours);
        return new ResponseEntity<>(inscriptions.stream().map(f->modelMapper.map(f, requestInscription.class)).toList(), HttpStatus.OK);
    }

}
