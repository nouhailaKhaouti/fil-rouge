package com.example.filRouge.service.diplomeService;

import com.example.filRouge.entities.Diplome;

import java.util.List;
import java.util.Optional;

public interface DiplomeService {
    Diplome create(Diplome Diplome);

/*
    Diplome update(Diplome Diplome);
*/

    void delete(Diplome Diplome);

    Optional<Diplome> findById(Diplome Diplome);

    Diplome findByRef(Diplome Diplome);

    List<Diplome> findAll();

    List<Diplome> findByInscription(Diplome diplome);
}
