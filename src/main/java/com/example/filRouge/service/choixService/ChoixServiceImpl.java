package com.example.filRouge.service.choixService;

import com.example.filRouge.Repository.ChoixRepository;
import com.example.filRouge.entities.Choix;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Inscription;
import com.example.filRouge.entities.Result;
import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.service.Result.ResultService;
import com.example.filRouge.service.concourService.concourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChoixServiceImpl implements ChoixService {
    final private ChoixRepository choixRepository;
    final private concourService concourService;
    final private ResultService resultService;
    @Override
    public Choix create(Choix choix) {
        Concour concour=concourService.findByReference(choix.getConcour().getReference());
        if(LocalDate.now().isAfter(concour.getDateConcoursEcrit().minusDays(3))){
            throw  new CustomException("you can't register , the deathline is over", HttpStatus.BAD_REQUEST);
        }
        if(choixRepository.findByInscriptionAndConcour(choix.getInscription(),concour)!=null) {
            throw new AlreadyExistException();
        }

        if(choix.getInscription().getNiveau().equals(concour.getNiveau())) {
            throw new CustomException("you can't have this choice as it's for an other type of applications", HttpStatus.BAD_REQUEST);
        }

        if(choixRepository.countChoixByInscription(choix.getInscription())>3){
            throw new CustomException("you can't have more then three choices", HttpStatus.BAD_REQUEST);
        }

        final Double[] resultPreselection = {0.0};
        choix.getInscription().getDiplomes().forEach(diplome -> {
            resultPreselection[0] += diplome.getNote();
        });

        Choix choixNew=choixRepository.save(choix);

        String locationAbbreviation = choix.getConcour().getReference().toUpperCase();
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        String generatedName = locationAbbreviation + "-" + formattedDate;
        resultService.saveResult(Result.builder().notePreselection(resultPreselection[0]/choix.getInscription().getDiplomes().size()).choix(choixNew).resultRef(generatedName).build());

        return choixNew;
    }

    @Override
    public Choix findByInscriptionAndConcours(Inscription inscription, Concour concour){
        Concour concour1=concourService.findByReference(concour.getReference());
        return choixRepository.findByInscriptionAndConcour(inscription,concour1);
    }

    @Override
   public List<Inscription> findByConcours(Concour concour){
       return  choixRepository.findByConcour(concour).stream().map(Choix::getInscription).collect(Collectors.toList());
   }

   @Override
   public  List<Inscription> PeselectionListByConcours(Concour concour){
        return choixRepository.findChoixWithInscriptionByConcourAndPreselection(concour).stream().map(Choix::getInscription).collect(Collectors.toList());
   }

   @Override
    public  List<Inscription> WritingListByConcours(Concour concour){
        return choixRepository.findChoixWithInscriptionByConcourAndWriting(concour).stream().map(Choix::getInscription).collect(Collectors.toList());
    }

    @Override
    public  List<Inscription> AdmisListByConcours(Concour concour){
        return choixRepository.findChoixWithInscriptionByConcourAndAdmis(concour).stream().map(Choix::getInscription).collect(Collectors.toList());
    }
    @Override
    public void delete(Choix choix) {
        choixRepository.delete(choix);
    }



}
