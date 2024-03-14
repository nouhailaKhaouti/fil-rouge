package com.example.filRouge.service.concourService;

import com.example.filRouge.Repository.ConcourRepository;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Filiere;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.filiereService.filiereService;
import com.example.filRouge.service.moduleservice.moduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            concourRepository.save(concours);
            concours.getModules().forEach(module -> {
                module.setConcour(concours);
                moduleService.saveModule(module);
            });

        return concours;
    }

    @Override
    public List<Concour> findByAnneeConcour(int anneeConcour) {
        return concourRepository.findByAnneeConcours(anneeConcour);
    }

    @Override
    public List<Concour> findByCreteria(String reference, String refFiliere, String anneeConcour) {
        if(reference!=null && anneeConcour!=null) {

            if(refFiliere!=null){
                Filiere filiere=filiereService.findByLabel(refFiliere);
                return concourRepository.findByReferenceAndFiliereAndAnneeConcours(reference, filiere, Integer.parseInt(anneeConcour));
            }
            return concourRepository.findByReferenceAndAnneeConcours(reference, Integer.parseInt(anneeConcour));

        } else if (reference!=null) {
            return List.of(findByReference(reference));
        } else if (anneeConcour!=null) {
            return findByAnneeConcour(Integer.parseInt(anneeConcour));
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
