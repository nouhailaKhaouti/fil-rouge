package com.example.filRouge.service.choixService;

import com.example.filRouge.entities.Choix;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Inscription;

import java.util.List;
import java.util.Optional;

public interface ChoixService {
    Choix create(Choix Choix);

/*
    Choix update(Choix Choix);
*/

    Choix findByInscriptionAndConcours(Inscription inscription, Concour concour);

    List<Inscription> findByConcours(Concour concour);

    List<Inscription> PeselectionListByConcours(Concour concour);

    List<Inscription> WritingListByConcours(Concour concour);

    List<Inscription> AdmisListByConcours(Concour concour);

    boolean countPeselectionListByConcours(Concour concour);

    Boolean countWritingListByConcours(Concour concour);

    Boolean countAdmisListByConcours(Concour concour);

    void delete(Choix Choix);

/*    Choix findByInscriptionAndConcour(Choix Choix);


    List<Choix> findAll();
    List<Choix> findByInscription();*/

}
