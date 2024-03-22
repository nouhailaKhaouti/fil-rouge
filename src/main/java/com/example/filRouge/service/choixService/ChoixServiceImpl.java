package com.example.filRouge.service.choixService;

import com.example.filRouge.Repository.ChoixRepository;
import com.example.filRouge.entities.Choix;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.concourService.concourService;
import com.example.filRouge.service.semestreService.SemestreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoixServiceImpl implements ChoixService {
    final private ChoixRepository choixRepository;
    final private concourService concourService;
    @Override
    public Choix create(Choix choix) {
        Concour concour=concourService.findByReference(choix.getConcour().getReference());
        if(choixRepository.findByInscriptionAndConcour(choix.getInscription(),concour)!=null) {
            throw new AlreadyExistException();
        }

        if(choix.getInscription().getNiveau().equals(concour.getNiveau())) {
            throw new CustomException("you can't have this choice as it's for an other type of applications", HttpStatus.BAD_REQUEST);
        }

        if(choixRepository.countChoixByInscription(choix.getInscription())>3){
            throw new CustomException("you can't have more then three choices", HttpStatus.BAD_REQUEST);
        }
        choix.setConcour(concourService.findByReference(choix.getConcour().getReference()));
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
