package com.example.filRouge.service.concourService;

import com.example.filRouge.Repository.ConcourRepository;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.entities.Module;
import com.example.filRouge.entities.Niveau;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.exception.DateValidationException;
import com.example.filRouge.service.filiereService.filiereService;
import com.example.filRouge.service.moduleservice.moduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

class concourserviceimpltest {

    private concourServiceImpl concourService;

    private ConcourRepository concourRepository;

    @BeforeEach
    void setUp() {
        concourRepository = mock(ConcourRepository.class);
        filiereService filiereService = mock(com.example.filRouge.service.filiereService.filiereService.class);
        moduleService moduleService = mock(com.example.filRouge.service.moduleservice.moduleService.class);
        concourService = new concourServiceImpl(concourRepository, moduleService, filiereService);
    }


    private Concour createConcour(){
        // Creating modules
        Module module1 = new Module();
        module1.setCoefModule(3);
        module1.setReference("Module1");

        Module module2 = new Module();
        module2.setCoefModule(2);
        module2.setReference("Module2");

        List<Module> modules = new ArrayList<>();
        modules.add(module1);
        modules.add(module2);

        Concour concour = new Concour();
        concour.setId(1L);
        concour.setReference("Concour1");
        concour.setAnneeConcours(2024);
        concour.setDateConcoursEcrit(LocalDate.of(2024, 6, 15));
        concour.setDateConcoursOral(LocalDate.of(2024, 6, 30));
        concour.setCreatedAt(LocalDate.now());
        concour.setNbreplace(100);
        concour.setNbreplaceConcoursEcrit(50);
        concour.setNbreplaceConcoursOral(50);
        concour.setFiliere(Filiere.builder().label("filiere").build());
        concour.setNiveau(Niveau.MASTER);
        concour.setModules(modules);
        return concour;
    }

    @Test
     void testAddConcoursWhenConcoursReferenceAlreadyFoundThenThrowNAlreadyFoundException(){
         Concour concour = createConcour();
         when(concourRepository.findByReference(anyString())).thenReturn(Optional.of(concour));
         assertThrows(AlreadyExistException.class, ()->concourService.saveConcourComplet(concour),"concour already exust");
    }

    @Test
    void testAddConcourWhenDateOralBeforeDateWritingThenThrowDateValidationException(){
        Concour concour = createConcour();
        when(concourRepository.findByReference(anyString())).thenReturn(Optional.empty());
        concour.setDateConcoursEcrit(LocalDate.of(2024, 3, 29));
        concour.setDateConcoursOral(LocalDate.of(2024, 3, 20));
        assertThrows(DateValidationException.class, ()->concourService.saveConcourComplet(concour),"date oral before date writing");
    }

    @Test
    void testAddConcoursWhenConcoursInTheSameDateExistThenThrowCustomException(){
        Concour concour = createConcour();
        List<Concour> concourList=new ArrayList<>();
        concourList.add(concour);
        when(concourRepository.findByReference(anyString())).thenReturn(Optional.empty());
        concour.setDateConcoursEcrit(LocalDate.now().minusDays(1));
        assertThrows(DateValidationException.class, ()->concourService.saveConcourComplet(concour),"date oral before date writing");
    }

    @Test
    void testAddConcoursIsSuccessful(){
        Concour concour = createConcour();
        when(concourRepository.findByReference(anyString())).thenReturn(Optional.empty());
        when(concourRepository.save(concour)).thenReturn(concour);
        assertEquals(concour,concourService.saveConcourComplet(concour));
    }

}