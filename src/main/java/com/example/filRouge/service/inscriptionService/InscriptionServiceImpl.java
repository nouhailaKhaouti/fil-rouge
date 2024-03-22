package com.example.filRouge.service.inscriptionService;

import com.example.filRouge.Repository.InscriptionRepository;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Inscription;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.choixService.ChoixService;
import com.example.filRouge.service.concourService.concourService;
import com.example.filRouge.service.diplomeService.DiplomeService;
import com.example.filRouge.service.semestreService.SemestreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {
    final private InscriptionRepository inscriptionRepository;
    final private ChoixService choixService;
    final private DiplomeService diplomeService;
    @Override
    public Inscription create(Inscription inscription) {



        List<Inscription> inscriptionList=inscriptionRepository.findByCinAndNiveau(inscription.getCin(),inscription.getNiveau());
        if(inscriptionList!=null){
            inscriptionList.forEach(inscription1->{
                if(LocalDate.now().getYear()==inscription1.getCreatedAt().getYear()){
                    throw new AlreadyExistException();
                }
            } );
        }

        inscription.setCreatedAt(LocalDate.now());
        Inscription inscriptionnew=inscriptionRepository.save(inscription);

        final Integer[] i = {1};

            if(inscriptionnew.getChoixs()!=null){
                inscriptionnew.getChoixs().forEach(choix -> {
                    choix.setInscription(inscriptionnew);
                    choix.setNumChoix(i[0]);
                    choixService.create(choix);
                    i[0]++;
                });
            }
            if(inscriptionnew.getDiplomes()!=null){
                inscriptionnew.getDiplomes().forEach(diplome -> {
                    diplome.setInscription(inscriptionnew);
                    diplomeService.create(diplome);
                });
            }
            return inscriptionnew;
    }

/*    @Override
    public Inscription update(Inscription inscription) {
        Optional<Inscription> optionalInscription=inscriptionRepository.findById(inscription.getId());
        if(opInscriptiontionalInscription.isPresent()){
            if(inscriptionRepository.findByLabel(inscription.getLabel()).isEmpty()){
                optionalInscription.get().setLabel(inscription.getLabel());
                inscriptionRepository.save(optionalInscription.get());
            }
            throw new AlreadyExistException();
        }
        throw new NotFoundException();
    }*/

    @Override
    public void delete(Inscription inscription) {
        inscription=findByRef(inscription);
        inscriptionRepository.delete(inscription);
    }

    @Override
    public Optional<Inscription> findById(Inscription inscription) {
        return inscriptionRepository.findById(inscription.getId());
    }

    @Override
    public Inscription findByRef(Inscription inscription) {
        List<Inscription> optionalInscription=inscriptionRepository.findByCinAndNiveau(inscription.getCin(),inscription.getNiveau());
        if(optionalInscription.isEmpty()){
            throw new NotFoundException();
        }
        return optionalInscription.get(0);
    }

    @Override
    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

}
