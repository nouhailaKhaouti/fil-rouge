package com.example.taskflow.services.concour;

import com.example.taskflow.domaine.Concour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ConcourService {
    Concour create(Concour concour);

    Concour update(Concour concour);

    void delete(Concour concour);
    List<Concour> getAll();

    Optional<Concour> findByCode(Concour concour);

    List<Concour> findAll(int page, int pageSize);

    Page<Concour> searchConcour(String keySearch, Pageable page);
}
