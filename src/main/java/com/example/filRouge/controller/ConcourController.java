package com.example.filRouge.controller;


import com.example.filRouge.controller.vm.concour.requestConcour;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.service.concourService.concourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/concour")
public class ConcourController {
    final private concourService concourService;

    final ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> addConcour(@Valid @RequestBody() requestConcour concour) {
        Concour concour1=modelMapper.map(concour, Concour.class);
        return ResponseEntity.ok(concourService.saveConcourComplet(concour1));
    }
}
