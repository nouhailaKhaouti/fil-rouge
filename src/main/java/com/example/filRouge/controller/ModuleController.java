package com.example.filRouge.controller;

import com.example.filRouge.controller.vm.module.requestModule;
import com.example.filRouge.entities.Module;
import com.example.filRouge.service.concourService.concourService;
import com.example.filRouge.service.moduleservice.moduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("FilRouge/api/member")
@PreAuthorize("hasRole('MANAGER')")
public class ModuleController {

    final private moduleService moduleService;
    final private concourService concourService;

    final ModelMapper modelMapper;

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/")
    public ResponseEntity<?> addModule(@Valid @RequestBody() requestModule requestModule){
        Module module=modelMapper.map(requestModule,Module.class);
        module.setConcour(concourService.findByReference(requestModule.getReference()));
        return ResponseEntity.ok(moduleService.saveModule(module));
    }

    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{reference}")
    public ResponseEntity<?> deleteModule(@PathVariable("reference") String reference) {
        Module module= Module.builder().reference(reference).build();
        moduleService.DeleteModule(module);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
