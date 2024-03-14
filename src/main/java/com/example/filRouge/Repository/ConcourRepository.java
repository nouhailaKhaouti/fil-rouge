package com.example.filRouge.Repository;

import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcourRepository extends JpaRepository<Concour, Long> {

    List<Concour> findByReferenceAndFiliereAndAnneeConcours(String reference, Filiere filiere,int annee);
    List<Concour> findByReferenceAndAnneeConcours(String reference,int annee);
    Optional<Concour> findByReference(String reference);
    List<Concour> findByFiliere(Filiere filiere);
    List<Concour> findByAnneeConcours(int anneeConcours);

}
