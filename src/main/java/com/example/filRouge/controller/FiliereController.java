package com.example.filRouge.controller;

import com.example.filRouge.controller.vm.filiere.FiliereVm;
import com.example.filRouge.entities.Departement;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.service.filiereService.filiereService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/filiere")
@PreAuthorize("hasRole('MANAGER')")
public class FiliereController {

    final private filiereService filiereService;

    final ModelMapper modelMapper;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/")
    public ResponseEntity<?> addFiliere(@Valid  @RequestBody() FiliereVm requestfiliere)  {
            Filiere filiere=modelMapper.map(requestfiliere,Filiere.class);
            return ResponseEntity.ok(filiereService.create(filiere));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{label}/{departement}")
    public ResponseEntity<?> deleteFiliere(@PathVariable("label") String label,@PathVariable("departement") String departement) {
            filiereService.delete(Filiere.builder().label(label).departement((Departement.builder().label(departement).build())).build());
            return new ResponseEntity<>(HttpStatus.OK);
    }



}
