package com.example.filRouge.service.departementSevice;

import com.example.filRouge.Repository.DepartementRepository;
import com.example.filRouge.entities.Departement;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class departementServiceImpl implements departementService{
    final private DepartementRepository departementRepository;
    @Override
    public Departement create(Departement departement) {
        if(departementRepository.findByLabel(departement.getLabel()).isEmpty()){
            departementRepository.save(departement);
        }
        throw new AlreadyExistException();
    }

    @Override
    public Departement update(Departement departement) {
        Optional<Departement> optionalDepartement=departementRepository.findById(departement.getId());
        if(optionalDepartement.isPresent()){
            if(departementRepository.findByLabel(departement.getLabel()).isEmpty()){
                optionalDepartement.get().setLabel(departement.getLabel());
                departementRepository.save(optionalDepartement.get());
            }
            throw new AlreadyExistException();
        }
        throw new NotFoundException();
    }

    @Override
    public void delete(Departement departement) {
        departementRepository.delete(departement);
    }

    @Override
    public Optional<Departement> findById(Departement departement) {
        return departementRepository.findById(departement.getId());
    }

    @Override
    public List<Departement> findAll() {
        return departementRepository.findAll();
    }
}
