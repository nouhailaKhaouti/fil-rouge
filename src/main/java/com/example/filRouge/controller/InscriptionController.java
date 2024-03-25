package com.example.filRouge.controller;


import com.example.filRouge.controller.vm.inscription.requestInscription;
import com.example.filRouge.controller.vm.inscription.respondeInscription;
import com.example.filRouge.entities.Inscription;
import com.example.filRouge.entities.Niveau;
import com.example.filRouge.service.concourService.concourService;
import com.example.filRouge.service.inscriptionService.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/inscription")
public class InscriptionController {
    final private InscriptionService inscriptionService;

    final ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<?> getAllInscriptions() {
        List<Inscription> inscriptionList = inscriptionService.findAll();
        return new ResponseEntity<>(inscriptionList.stream().map(f->modelMapper.map(f, respondeInscription.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/{cin}/{niveau}")
    public ResponseEntity<?> getInscriptions(@PathVariable("cin") String cin, @PathVariable("niveau")Niveau niveau) {
        Inscription inscription = inscriptionService.findByRef(Inscription.builder().cin(cin).niveau(niveau).build());
        return new ResponseEntity<>(modelMapper.map(inscription, respondeInscription.class), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addInscription( @RequestBody() requestInscription requestInscription ) {
        Inscription inscription=inscriptionService.create(modelMapper.map(requestInscription, Inscription.class));
        return new ResponseEntity<>(inscription, HttpStatus.OK);
    }
}
