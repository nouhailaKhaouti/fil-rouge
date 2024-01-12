package com.example.taskflow.repository;

import com.example.taskflow.domaine.Concour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcourRepository extends JpaRepository<Concour,String> {
 Optional<Concour> findByCode(Concour concour);
}
