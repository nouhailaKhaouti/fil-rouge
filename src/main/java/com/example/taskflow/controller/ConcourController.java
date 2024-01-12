package com.example.taskflow.controller;

import com.example.taskflow.domaine.Concour;
import com.example.taskflow.services.concour.ConcourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/concour")
@PreAuthorize("hasRole('ADMIN') AND hasRole('MANAGER')")
@RequiredArgsConstructor
public class ConcourController {
    final private ConcourService concourService;
    @GetMapping("/")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(concourService.getAll(),HttpStatus.OK);
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('concour:create')")
    public ResponseEntity<?> post(@RequestBody Concour concour) {
        return new ResponseEntity<>(concourService.create(concour),HttpStatus.OK);
    }
    @PutMapping("/")
    @PreAuthorize("hasAuthority('concour:update')")
    public ResponseEntity<?> put(@RequestBody Concour concour) {
        return new ResponseEntity<>(concourService.update(concour),HttpStatus.OK);
    }
    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('concour:delete')")
    public ResponseEntity<?> delete(@RequestBody Concour concour) {
        concourService.delete(concour);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}