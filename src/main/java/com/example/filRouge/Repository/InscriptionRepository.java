package com.example.filRouge.Repository;

import com.example.filRouge.entities.Inscription;
import com.example.filRouge.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {

    List<Inscription> findByCinAndNiveau(String cin, Niveau niveau);



}
