package com.example.filRouge.service.departementSevice;

import com.example.filRouge.entities.Departement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface departementService {
    Departement create(Departement departement);

    Departement update(Departement departement);

    void delete(Departement departement);

    Optional<Departement> findById(Departement departement);

    Departement findByLabel(Departement departement);

    List<Departement> findAll();
}
