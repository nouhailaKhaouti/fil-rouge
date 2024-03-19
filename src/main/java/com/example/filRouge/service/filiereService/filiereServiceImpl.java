package com.example.filRouge.service.filiereService;

import com.example.filRouge.Repository.FiliereRepository;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.departementSevice.departementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class filiereServiceImpl implements filiereService{
    final private FiliereRepository filiereRepository;
    final private departementService departementService;
    @Override
    public Filiere create(Filiere filiere) {

        if(filiereRepository.findByLabelAndDepartement(filiere.getLabel(),departementService.findByLabel(filiere.getDepartement())).isEmpty()){
            return filiereRepository.save(filiere);
        }
        throw new AlreadyExistException();
    }

    @Override
    public Filiere update(Filiere filiere) {
        Optional<Filiere> optionalFiliere=filiereRepository.findById(filiere.getId());
        if(optionalFiliere.isPresent()){
            if(filiereRepository.findByLabel(filiere.getLabel()).isEmpty()){
                optionalFiliere.get().setLabel(filiere.getLabel());
                return filiereRepository.save(optionalFiliere.get());
            }
            throw new AlreadyExistException();
        }
        throw new NotFoundException();
    }

    @Override
    public void delete(Filiere filiere) {
        if(filiereRepository.findByLabelAndDepartement(filiere.getLabel(),departementService.findByLabel(filiere.getDepartement())).isPresent()){
            filiereRepository.delete(findByLabel(filiere.getLabel()));
        }

        throw new NotFoundException();
    }

    @Override
    public Filiere findById(Filiere filiere) {
        Optional<Filiere> optionalFiliere=filiereRepository.findById(filiere.getId());
        if(optionalFiliere.isEmpty()){
            throw new NotFoundException();
        }
        return optionalFiliere.get();
    }

    @Override
    public Filiere findByLabel(String filiere) {
        Optional<Filiere> optionalFiliere=filiereRepository.findByLabel(filiere);
        if(optionalFiliere.isEmpty()){
            throw new NotFoundException();
        }
        return optionalFiliere.get();
    }

    @Override
    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }
}
