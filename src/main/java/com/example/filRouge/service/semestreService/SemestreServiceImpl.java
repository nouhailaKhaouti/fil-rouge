package com.example.filRouge.service.semestreService;

import com.example.filRouge.Repository.SemestreRepository;
import com.example.filRouge.entities.Semestre;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SemestreServiceImpl implements SemestreService {
    final private SemestreRepository semestreRepository;
    @Override
    public Semestre create(Semestre semestre) {
        if(semestreRepository.findByRefSemestreAAndDiplome(semestre.getRefSemestre(),semestre.getDiplome()).isEmpty()){
            return semestreRepository.save(semestre);
        }
        throw new AlreadyExistException();
    }

/*    @Override
    public Semestre update(Semestre semestre) {
        Optional<Semestre> optionalSemestre=semestreRepository.findById(semestre.getId());
        if(optionalSemestre.isPresent()){
            if(semestreRepository.findByLabel(semestre.getLabel()).isEmpty()){
                optionalSemestre.get().setLabel(semestre.getLabel());
                semestreRepository.save(optionalSemestre.get());
            }
            throw new AlreadyExistException();
        }
        throw new NotFoundException();
    }*/

    @Override
    public void delete(Semestre semestre) {
        semestre=findByRef(semestre);
        semestreRepository.delete(semestre);
    }

    @Override
    public Optional<Semestre> findById(Semestre semestre) {
        return semestreRepository.findById(semestre.getId());
    }

    @Override
    public Semestre findByRef(Semestre semestre) {
        Optional<Semestre> optionalSemestre=semestreRepository.findByRefSemestreAAndDiplome(semestre.getRefSemestre(),semestre.getDiplome());
        if(optionalSemestre.isEmpty()){
            throw new NotFoundException();
        }
        return optionalSemestre.get();
    }

    @Override
    public List<Semestre> findAll() {
        return semestreRepository.findAll();
    }
}
