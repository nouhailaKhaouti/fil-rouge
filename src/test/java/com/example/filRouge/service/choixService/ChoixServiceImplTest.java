package com.example.filRouge.service.choixService;

import com.example.filRouge.Repository.ChoixRepository;
import com.example.filRouge.entities.*;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.service.Result.ResultService;
import com.example.filRouge.service.concourService.concourService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ChoixServiceImplTest {

    private ChoixRepository choixRepository;

    private ChoixServiceImpl choixService;

    private concourService concourService;
    private ResultService resultService;

    @BeforeEach
    void setUp() {
        choixRepository = mock(ChoixRepository.class);
        concourService = mock(concourService.class);
        resultService = mock(ResultService.class);
        choixService = new ChoixServiceImpl(choixRepository, concourService, resultService);
    }


   private Choix createChoix(){

       Choix choix = new Choix();
       choix.setNumChoix(1);
       choix.setInscription(createInscription());

       Result result = new Result();
       result.setResultRef("Result_Ref");
       result.setPreselectione(false);

       choix.setResult(result);
       choix.setConcour(createConcour());
        return choix;
   }

   private Concour createConcour(){
       Concour concour = new Concour();
       concour.setId(1L);
       concour.setReference("Concour 1");
       concour.setAnneeConcours(2024);
       concour.setDateConcoursEcrit(LocalDate.of(2024, 6, 15));
       concour.setDateConcoursOral(LocalDate.of(2024, 6, 30));
       concour.setCreatedAt(LocalDate.now());
       concour.setNbreplace(100);
       concour.setNbreplaceConcoursEcrit(50);
       concour.setNbreplaceConcoursOral(50);
       concour.setFiliere(Filiere.builder().label("filiere").build());
       concour.setNiveau(Niveau.MASTER);
       return concour;
   }

   private Inscription createInscription(){
       Inscription inscription = new Inscription();
       inscription.setCne("123456789"); // Set the CNE attribute
       inscription.setNom("John"); // Set the nom attribute
       inscription.setPrenom("Doe"); // Set the prenom attribute
       inscription.setCin("AB123456"); // Set the cin attribute
       inscription.setTel("+1234567890"); // Set the tel attribute
       inscription.setEmail("john.doe@example.com"); // Set the email attribute
       inscription.setNiveau(Niveau.MASTER); // Set the niveau attribute
       inscription.setAdressePersonnelle("123 Street, City"); // Set the AdressePersonnelle attribute
       inscription.setStatue("Active"); // Set the statue attribute
       inscription.setDateNaissance(LocalDate.of(1990, 1, 1)); // Set the dateNaissance attribute
       inscription.setCreatedAt(LocalDate.now()); // Set the createdAt attribute
       return inscription;
   }


    @Test
    void testAddChoixWhenConcoursReferenceAlreadyFoundThenThrowNAlreadyFoundException(){
        Concour concour = createConcour();
        when(concourService.findByReference(any())).thenReturn(concour);
        when(choixService.findConcour(concour)).thenReturn(concour);
        concour.setDateConcoursEcrit(LocalDate.now().plusDays(1));
        assertThrows(CustomException.class, ()->choixService.create(createChoix()),"concour already exust");
    }

    @Test
    void testAddChoixWhenChoixAlreadyFoundThenThrowNAlreadyFoundException(){
        Concour concour = createConcour();
        when(concourService.findByReference(any())).thenReturn(concour);
        when(choixService.findConcour(concour)).thenReturn(concour);
        when(choixRepository.findByInscriptionAndConcour(any(),any())).thenReturn(createChoix());
        assertThrows(AlreadyExistException.class, ()->choixService.create(createChoix()),"concour already exust");
    }

    @Test
    void testAddChoixWhenChoixNiveauDifferentThenConcoursNiveauThenThrowNAlreadyFoundException(){
        Concour concour = createConcour();
        Choix choix=createChoix();
        when(concourService.findByReference(any())).thenReturn(concour);
        when(choixService.findConcour(concour)).thenReturn(concour);
        when(choixRepository.findByInscriptionAndConcour(any(),any())).thenReturn(null);
        choix.getInscription().setNiveau(Niveau.LICENCE);
        assertThrows(CustomException.class, ()->choixService.create(choix),"concour already exust");
    }

}