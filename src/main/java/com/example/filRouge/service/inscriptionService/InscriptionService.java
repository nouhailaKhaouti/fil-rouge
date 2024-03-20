package com.example.filRouge.service.inscriptionService;

import com.example.filRouge.entities.Inscription;

import java.util.List;
import java.util.Optional;

public interface InscriptionService {
    Inscription create(Inscription Inscription);

/*
    Inscription update(Inscription Inscription);
*/

    void delete(Inscription Inscription);

    Optional<Inscription> findById(Inscription Inscription);

    Inscription findByRef(Inscription Inscription);

    List<Inscription> findAll();

}
