package com.example.filRouge.service.inscriptionService;

import com.example.filRouge.Repository.InscriptionRepository;
import com.example.filRouge.entities.Inscription;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.semestreService.SemestreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {
    final private InscriptionRepository inscriptionRepository;
    final private SemestreService semestreService;
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
            Inscription inscriptionnew=inscriptionRepository.save(inscription);
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
