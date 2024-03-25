package com.example.filRouge.controller;

import com.example.filRouge.controller.vm.filiere.FiliereVm;
import com.example.filRouge.entities.Departement;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.service.departementSevice.departementService;
import com.example.filRouge.service.filiereService.filiereService;
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
@RequestMapping("FilRouge/api/filiere")
public class FiliereController {

    final private filiereService filiereService;
    final private departementService departementService;

    final ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<?> getAllDepartements() {
        List<Filiere> filieres = filiereService.findAll();
        return new ResponseEntity<>(filieres.stream().map(f->modelMapper.map(f, FiliereVm.class)).toList(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/")
    public ResponseEntity<?> addFiliere(@Valid  @RequestBody() FiliereVm requestfiliere)  {
       return new ResponseEntity<>(filiereService.create(Filiere.builder().label(requestfiliere.getLabel()).departement(departementService.findByLabel(Departement.builder().label(requestfiliere.getDepartement().getLabel()).build())).build()),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{label}/{departement}")
    public ResponseEntity<?> deleteFiliere(@PathVariable("label") String label,@PathVariable("departement") String departement) {
            filiereService.delete(Filiere.builder().label(label).departement(Departement.builder().label(departement).build()).build());
            return new ResponseEntity<>(HttpStatus.OK);
    }



}
