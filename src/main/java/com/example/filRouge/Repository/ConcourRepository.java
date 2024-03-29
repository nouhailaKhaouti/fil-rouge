package com.example.filRouge.Repository;

import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcourRepository extends JpaRepository<Concour, Long> {

    List<Concour> findByFiliereAndAnneeConcours(Filiere filiere,Integer annee);
    List<Concour> findByFiliereAndNiveau(Filiere filiere, Niveau niveau);
    List<Concour> findByReferenceAndAnneeConcours(String reference,Integer annee);
    Optional<Concour> findByReference(String reference);
    List<Concour> findByFiliere(Filiere filiere);
    List<Concour> findByAnneeConcours(int anneeConcours);

}
