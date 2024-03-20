package com.example.filRouge.service.semestreService;

import com.example.filRouge.entities.Semestre;

import java.util.List;
import java.util.Optional;

public interface SemestreService {
    Semestre create(Semestre Semestre);

/*
    Semestre update(Semestre Semestre);
*/

    void delete(Semestre Semestre);

    Optional<Semestre> findById(Semestre Semestre);

    Semestre findByRef(Semestre Semestre);

    List<Semestre> findAll();
}
