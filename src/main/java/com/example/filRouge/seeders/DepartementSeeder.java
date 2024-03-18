package com.example.filRouge.seeders;

import com.example.filRouge.Repository.DepartementRepository;
import com.example.filRouge.entities.Departement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartementSeeder {
    final private DepartementRepository departementRepository;
    public void seedDepartements() {
        if (departementRepository.count() == 0) {
            String[] departementLabels = {"Department A", "Department B", "Department C", "Department D", "Department E"};

            for (String departementLabel : departementLabels) {
                Departement departement = new Departement();
                departement.setLabel(departementLabel);
                departementRepository.save(departement);
            }
        }
    }
}
