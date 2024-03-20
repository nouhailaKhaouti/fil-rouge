package com.example.filRouge.service.concourService;

import com.example.filRouge.Repository.ConcourRepository;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.exception.DateValidationException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.filiereService.filiereService;
import com.example.filRouge.service.moduleservice.moduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class concourServiceImpl implements concourService{
     final private ConcourRepository concourRepository;
     final private moduleService moduleService;
     final private filiereService filiereService;

    @Override
    public Concour findByReference(String reference) {
        return concourRepository.findByReference(reference).get();
    }

    @Override
    public List<Concour> findByRefFiliere(Filiere Filiere) {
        return concourRepository.findByFiliere(Filiere);
    }

    @Override
    public List<Concour> findAll() {
        return concourRepository.findAll();
    }

    @Override
    public Concour DeleteConcour(Concour concours) {

        Optional<Concour> c = concourRepository.findByReference(concours.getReference());
         if(c.isEmpty()){
             throw new NotFoundException();
         }
        concourRepository.delete(c.get());
        return concours;
    }

    @Override
    public Concour saveConcourComplet(Concour concours) {

        Optional<Concour> c = concourRepository.findByReference(concours.getReference());

        if(c.isPresent()){
            throw new AlreadyExistException();
        }

        List<Concour> concourList=findByCreteria(concours.getFiliere().getLabel(),concours.getAnneeConcours());
        concourList.forEach(concour -> {
            if(concours.getNiveau().equals(concour.getNiveau())) {
                if (concours.getDateConcoursEcrit().isAfter(concour.getDateConcoursEcrit()) &&
                        concours.getDateConcoursEcrit().isBefore(concour.getDateConcoursOral())) {
                    throw new CustomException("It look like there's an other exam  ", HttpStatus.BAD_REQUEST);
                }
            }
        });

        if(concours.getDateConcoursEcrit().isAfter(LocalDate.now().plusDays(7)) ){
            if(concours.getDateConcoursEcrit().isAfter(concours.getDateConcoursOral())) {
                concours.setFiliere(filiereService.findByLabel(concours.getFiliere().getLabel()));
                Concour concoursNew = concourRepository.save(concours);
                concoursNew.getModules().forEach(module -> {
                    module.setConcour(concoursNew);
                    moduleService.saveModule(module);
                });

                return concoursNew;
            }
            throw new CustomException("the oral exam day can't be before the writing exam Date", HttpStatus.BAD_REQUEST);
        }
        throw new DateValidationException();
    }

    @Override
    public List<Concour> findByAnneeConcour(Integer anneeConcour) {
        return concourRepository.findByAnneeConcours(anneeConcour);
    }

    @Override
    public List<Concour> findByCreteria(String refFiliere, Integer anneeConcour) {
        if(anneeConcour!=null) {
            if(refFiliere!=null){
                Filiere filiere=filiereService.findByLabel(refFiliere);
                return concourRepository.findByFiliereAndAnneeConcours(filiere, anneeConcour);
            }
            return findByAnneeConcour(anneeConcour);
        }
        return findAll();
    }

    @Override
    public Concour updateConcour(Concour concours) {
        Optional<Concour> c = concourRepository.findByReference(concours.getReference());

        if (c.isEmpty()) {
            throw new NotFoundException();
        }
            Concour concour=c.get();
        if(concours.getDateConcoursEcrit()!=null) {
            concour.setDateConcoursEcrit(concours.getDateConcoursEcrit());
        }
        if(concours.getDateConcoursOral()!=null) {
            concour.setDateConcoursOral(concours.getDateConcoursOral());
        }
            concour.setNbreplace(concours.getNbreplace());
            concour.setNbreplaceConcoursEcrit(concours.getNbreplaceConcoursEcrit());
            concour.setNbreplaceConcoursOral(concours.getNbreplaceConcoursOral());

        concourRepository.save(concour);
        return null;
    }


}
