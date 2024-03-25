package com.example.filRouge.Repository;

import com.example.filRouge.entities.Choix;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Inscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChoixRepository extends JpaRepository<Choix, Long> {

  Integer countChoixByInscription(Inscription inscription);

  List<Choix> findByInscription(Inscription inscription);
  List<Choix> findByConcour(Concour concour);

  Choix findByInscriptionAndConcour(Inscription inscription, Concour concour);

  @Query("SELECT ch FROM Choix ch JOIN FETCH ch.inscription ins JOIN FETCH ch.result r WHERE ch.concour = :concour AND r.preselectione = true")
  List<Choix> findChoixWithInscriptionByConcourAndPreselection(@Param("concour") Concour concour);

  @Query("SELECT ch FROM Choix ch JOIN FETCH ch.inscription ins JOIN FETCH ch.result r WHERE ch.concour = :concour AND r.retenueOral = true")
  List<Choix> findChoixWithInscriptionByConcourAndWriting(@Param("concour") Concour concour);
  @Query("SELECT ch FROM Choix ch JOIN FETCH ch.inscription ins JOIN FETCH ch.result r WHERE ch.concour = :concour AND r.admis = true")
  List<Choix> findChoixWithInscriptionByConcourAndAdmis(@Param("concour") Concour concour);

  @Query("SELECT ch FROM Choix ch JOIN FETCH ch.inscription ins JOIN FETCH ch.result r WHERE ch.concour = :concour ORDER BY ch.result.noteOral DESC")
  List<Choix> findChoixWithInscriptionByConcourAndAdmisNbrPlace(@Param("concour") Concour concour, Pageable pageable);

  @Query("SELECT ch FROM Choix ch JOIN FETCH ch.inscription ins JOIN FETCH ch.result r WHERE ch.concour = :concour ORDER BY ch.result.notePreselection DESC")
  List<Choix> findChoixWithInscriptionByConcourAndPreselectionNbrPlace(@Param("concour") Concour concour, Pageable pageable);

  @Query("SELECT ch FROM Choix ch JOIN FETCH ch.inscription ins JOIN FETCH ch.result r WHERE ch.concour = :concour ORDER BY ch.result.noteEcrit DESC")
  List<Choix> findChoixWithInscriptionByConcourAndWritingNbrPlace(@Param("concour") Concour concour, Pageable pageable);

  @Query(value = "SELECT COUNT(*) " +
          "FROM choix ch " +
          "JOIN inscription ins ON ch.inscription_id = ins.id " +
          "JOIN result r ON ch.result_id = r.id " +
          "WHERE ch.concour = :concour AND r.preselection = true",
          nativeQuery = true)
  Integer countChoixWithInscriptionByConcourAndPreselection(@Param("concour") Concour concour);

/*  @Query("SELECT count(*) FROM Choix ch JOIN FETCH ch.inscription ins JOIN FETCH ch.result r WHERE ch.concour = :concour AND r.retenueOral = true")
  Integer countChoixWithInscriptionByConcourAndWriting(@Param("concour") Concour concour);*/
  @Query(value = "SELECT COUNT(*) " +
          "FROM choix ch " +
          "JOIN inscription ins ON ch.inscription_id = ins.id " +
          "JOIN result r ON ch.result_id = r.id " +
          "WHERE ch.concour = :concour AND r.retenue_oral = true",
          nativeQuery = true)
  Integer countChoixWithInscriptionByConcourAndWriting(@Param("concour") Concour concour);
  @Query(value = "SELECT COUNT(*) " +
          "FROM choix ch " +
          "JOIN inscription ins ON ch.inscription_id = ins.id " +
          "JOIN result r ON ch.result_id = r.id " +
          "WHERE ch.concour = :concour AND r.admis = true",
          nativeQuery = true)  Integer countChoixWithInscriptionByConcourAndAdmis(@Param("concour") Concour concour);

}
