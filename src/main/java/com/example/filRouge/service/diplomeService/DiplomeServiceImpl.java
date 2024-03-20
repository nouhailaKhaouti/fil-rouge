package com.example.filRouge.service.diplomeService;

import com.example.filRouge.Repository.DiplomeRepository;
import com.example.filRouge.entities.Diplome;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiplomeServiceImpl implements DiplomeService {
    final private DiplomeRepository diplomeRepository;
    @Override
    public Diplome create(Diplome diplome) {
        if(diplomeRepository.findByRefDiplomeAndInscription(diplome.getRefDiplome(),diplome.getInscription()).isEmpty()){
            return diplomeRepository.save(diplome);
        }
        throw new AlreadyExistException();
    }

/*    @Override
    public Diplome update(Diplome diplome) {
        Optional<Diplome> optionalDiplome=diplomeRepository.findById(diplome.getId());
        if(opDiplometionalDiplome.isPresent()){
            if(diplomeRepository.findByLabel(diplome.getLabel()).isEmpty()){
                optionalDiplome.get().setLabel(diplome.getLabel());
                diplomeRepository.save(optionalDiplome.get());
            }
            throw new AlreadyExistException();
        }
        throw new NotFoundException();
    }*/

    @Override
    public void delete(Diplome diplome) {
        diplome=findByRef(diplome);
        diplomeRepository.delete(diplome);
    }

    @Override
    public Optional<Diplome> findById(Diplome diplome) {
        return diplomeRepository.findById(diplome.getId());
    }

    @Override
    public Diplome findByRef(Diplome diplome) {
        Optional<Diplome> optionalDiplome=diplomeRepository.findByRefDiplomeAndInscription(diplome.getRefDiplome(),diplome.getInscription());
        if(optionalDiplome.isEmpty()){
            throw new NotFoundException();
        }
        return optionalDiplome.get();
    }

    @Override
    public List<Diplome> findAll() {
        return diplomeRepository.findAll();
    }

    @Override
    public List<Diplome> findByInscription(Diplome diplome) {
        return diplomeRepository.findByInscription(diplome.getInscription());
    }
}
