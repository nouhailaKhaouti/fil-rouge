package com.example.filRouge.controller;

import com.example.filRouge.controller.vm.departement.DepartementVm;
import com.example.filRouge.entities.Departement;
import com.example.filRouge.entities.Role;
import com.example.filRouge.service.departementSevice.departementService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/departement")
@PreAuthorize("hasRole('MANAGER')")
public class DepartementController {

    final private departementService departementService;

    final ModelMapper modelMapper;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/")
    public ResponseEntity<?> addDepartement(@Valid  @RequestBody() DepartementVm requestdepartement)  {
            Departement departement=modelMapper.map(requestdepartement,Departement.class);
            return ResponseEntity.ok(departementService.create(departement));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartement(@PathVariable("id") long id, @RequestBody() Departement departement) {
            departement.setId(id);
            Departement addedDepartement = departementService.update(departement);
            return new ResponseEntity<>(addedDepartement, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{label}")
    public ResponseEntity<?> deleteDepartement(@PathVariable("label") String label) {
            Departement departement= Departement.builder().label(label).build();
            departementService.delete(departement);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
