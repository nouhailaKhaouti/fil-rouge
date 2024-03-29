package com.example.filRouge.service.concourService;

import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.entities.Niveau;
import org.springframework.stereotype.Service;

import java.util.List;

public interface concourService {
    public Concour saveConcourComplet(Concour concours);

    public Concour DeleteConcour(Concour concours);

    public Concour findByReference(String reference);

    public List<Concour> findByAnneeConcour(Integer anneeConcour);

    List<Concour> findByRefFiliere(Filiere Filiere);

    List<Concour> findByRefFiliereAndNiveau(Filiere Filiere, Niveau niveau);

    public List<Concour> findAll();

    public List<Concour> findByCreteria(String refFiliere, Integer anneeConcour);

    Concour updateConcour(Concour concours);
}
