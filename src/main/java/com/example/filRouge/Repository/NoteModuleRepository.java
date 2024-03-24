package com.example.filRouge.Repository;

import com.example.filRouge.entities.NoteModule;
import com.example.filRouge.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteModuleRepository extends JpaRepository<NoteModule, Long> {

    NoteModule findByResultAndRefModuleConcours(Result result,String reference);
}
