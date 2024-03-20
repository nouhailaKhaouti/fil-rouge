package com.example.filRouge.service.choixService;

import com.example.filRouge.Repository.ChoixRepository;
import com.example.filRouge.entities.Choix;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.semestreService.SemestreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoixServiceImpl implements ChoixService {
    final private ChoixRepository choixRepository;
    @Override
    public Choix create(Choix choix) {
        if(choixRepository.findByInscriptionAndConcour(choix.getInscription(),choix.getConcour())!=null) {
            throw new AlreadyExistException();
        }
        if(choixRepository.countChoixByInscription(choix.getInscription())>3){
            throw new CustomException("you can have more then three choices", HttpStatus.BAD_REQUEST);
        }
        return choixRepository.save(choix);

    }

/*    @Override
    public Choix update(Choix choix) {
        Optional<Choix> optionalChoix=choixRepository.findById(choix.getId());
        if(opChoixtionalChoix.isPresent()){
            if(choixRepository.findByLabel(choix.getLabel()).isEmpty()){
                optionalChoix.get().setLabel(choix.getLabel());
                choixRepository.save(optionalChoix.get());
            }
            throw new AlreadyExistException();
        }
        throw new NotFoundException();
    }*/

    @Override
    public void delete(Choix choix) {
        choixRepository.delete(choix);
    }



}
