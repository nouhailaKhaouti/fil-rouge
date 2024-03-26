package com.example.filRouge.controller;


import com.example.filRouge.controller.vm.concour.ResponseConcour;
import com.example.filRouge.controller.vm.concour.requestConcour;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.entities.Niveau;
import com.example.filRouge.service.concourService.concourService;
import jakarta.validation.Valid;
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

public class ConcourController {
    final private concourService concourService;

    final ModelMapper modelMapper;


    @PreAuthorize("hasRole('MANAGER') OR hasRole('PROF')")
    @GetMapping("/")
    public ResponseEntity<?> getAllConcours() {
        List<Concour> concours = concourService.findAll();
        return new ResponseEntity<>(concours.stream().map(f->modelMapper.map(f, ResponseConcour.class)).toList(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER') OR hasRole('PROF')")

    @GetMapping("/{reference}")
    public ResponseEntity<?> getConcours(@PathVariable("reference") String reference) {
        Concour concours = concourService.findByReference(reference);
        return new ResponseEntity<>(modelMapper.map(concours, ResponseConcour.class), HttpStatus.OK);
    }

    @GetMapping("/Filiere/{filiere}/{niveau}")
    public ResponseEntity<?> getAllConcoursByFiliere(@PathVariable("filiere") String filiere,@PathVariable("niveau") String niveau) {
        List<Concour> concours = concourService.findByRefFiliereAndNiveau(Filiere.builder().label(filiere).build(), Niveau.valueOf(niveau));
        return new ResponseEntity<>(concours.stream().map(f->modelMapper.map(f, ResponseConcour.class)).toList(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER') OR hasRole('PROF')")
    @PostMapping("/")
    public ResponseEntity<?> addConcour( @RequestBody() requestConcour concour) {
        Concour concour1=modelMapper.map(concour, Concour.class);
        return ResponseEntity.ok(concourService.saveConcourComplet(concour1));
    }
}
