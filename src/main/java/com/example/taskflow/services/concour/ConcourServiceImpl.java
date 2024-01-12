package com.example.taskflow.services.concour;

import com.example.taskflow.domaine.Concour;
import com.example.taskflow.repository.ConcourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcourServiceImpl implements ConcourService {
    final private ConcourRepository concourRepository;
    @Override
    public Concour create(Concour concour) {
        return concourRepository.save(concour);
    }

    @Override
    public Concour update(Concour concour) {
        return concourRepository.save(concour);
    }

    @Override
    public void delete(Concour concour) {
         concourRepository.delete(concour);
    }

    @Override
    public List<Concour> getAll() {
        return concourRepository.findAll();
    }

    @Override
    public Optional<Concour> findByCode(Concour concour) {
        return concourRepository.findByCode(concour);
    }

    @Override
    public List<Concour> findAll(int page, int pageSize) {
        return null;
    }

    @Override
    public Page<Concour> searchConcour(String keySearch, Pageable page) {
        return null;
    }
}
