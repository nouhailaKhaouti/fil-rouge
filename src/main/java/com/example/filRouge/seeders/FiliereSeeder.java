package com.example.filRouge.seeders;

import com.example.filRouge.Repository.DepartementRepository;
import com.example.filRouge.Repository.FiliereRepository;
import com.example.filRouge.entities.Departement;
import com.example.filRouge.entities.Filiere;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class FiliereSeeder {
    final private FiliereRepository filiereRepository;
    final private DepartementRepository departementRepository;
    public void seedFilieres() {
        if (filiereRepository.count() == 0) {
            List<Departement> departements = departementRepository.findAll();

            for (int i = 0; i < 10; i++) {
                Filiere filiere = new Filiere();
                filiere.setLabel("Filiere " + (i + 1));
                filiere.setDepartement(departements.get(ThreadLocalRandom.current().nextInt(0, departements.size()))); // Random departement
                filiereRepository.save(filiere);
            }
        }
    }
}
