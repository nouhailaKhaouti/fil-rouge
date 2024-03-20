package com.example.filRouge.Repository;

import com.example.filRouge.entities.Diplome;
import com.example.filRouge.entities.DiplomeName;
import com.example.filRouge.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiplomeRepository extends JpaRepository<Diplome, Long> {

    List<Diplome> findByInscription(Inscription inscription);
    Optional<Diplome> findByRefDiplomeAndInscription(DiplomeName refDiplome, Inscription inscription);
}
