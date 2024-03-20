package com.example.filRouge.Repository;

import com.example.filRouge.entities.Choix;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Inscription;
import com.example.filRouge.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChoixRepository extends JpaRepository<Choix, Long> {

  Integer countChoixByInscription(Inscription inscription);

  List<Choix> findByInscription(Inscription inscription);

  Choix findByInscriptionAndConcour(Inscription inscription, Concour concour);


}
