package com.example.filRouge.seeders;

import com.example.filRouge.Repository.ConcourRepository;
import com.example.filRouge.Repository.FiliereRepository;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class ConcourSeeder {

     final private ConcourRepository concourRepository;
     final private FiliereRepository filiereRepository;
    private LocalDate getRandomDate(LocalDate startDate, LocalDate endDate) {
        long startDays = startDate.toEpochDay();
        long endDays = endDate.toEpochDay();
        long randomDays = ThreadLocalRandom.current().nextLong(startDays, endDays);
        return LocalDate.ofEpochDay(randomDays);
    }


    public void seedConcours() {
        if (concourRepository.count() == 0) {
            List<Filiere> filieres = filiereRepository.findAll();
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();

            for (int i = 0; i < 10; i++) {
                Concour concour = new Concour();
                concour.setReference("Concour " + (i + 1));
                concour.setAnneeConcours(currentYear);
                concour.setDateConcoursEcrit(getRandomDate(currentDate, currentDate.plusYears(1))); // Random date within next year
                concour.setDateConcoursOral(getRandomDate(currentDate, currentDate.plusYears(1))); // Random date within next year
                concour.setNbreplace(ThreadLocalRandom.current().nextInt(1, 10));
                concour.setNbreplaceConcoursEcrit(ThreadLocalRandom.current().nextInt(1, 10));
                concour.setNbreplaceConcoursOral(ThreadLocalRandom.current().nextInt(1, 10));
                concour.setFiliere(filieres.get(ThreadLocalRandom.current().nextInt(0, filieres.size()))); // Random filiere
                concourRepository.save(concour);
            }
        }
    }
}