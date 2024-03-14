package com.example.filRouge.service.filiereService;

import com.example.filRouge.entities.Filiere;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface filiereService {
    Filiere create(Filiere filiere);

    Filiere update(Filiere filiere);

    void delete(Filiere filiere);

    Filiere findById(Filiere filiere);
    Filiere findByLabel(String filiere);

    List<Filiere> findAll();
}
