package com.example.filRouge.Repository;

import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByConcour(Concour concour);
    Optional<Module> findByReference(String reference);
}
