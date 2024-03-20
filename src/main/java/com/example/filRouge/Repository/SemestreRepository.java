package com.example.filRouge.Repository;

import com.example.filRouge.entities.Diplome;
import com.example.filRouge.entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {

    List<Semestre> findByDiplome(Diplome diplome);
    Optional<Semestre> findByRefSemestreAAndDiplome(String reference,Diplome diplome);
}
