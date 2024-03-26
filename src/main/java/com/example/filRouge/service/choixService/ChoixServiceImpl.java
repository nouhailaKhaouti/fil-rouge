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
import org.springframework.data.domain.PageRequest;
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
        Concour concour=this.findConcour(choix.getConcour());

        if(LocalDate.now().isAfter(concour.getDateConcoursEcrit().minusDays(3))){
            throw  new CustomException("you can't register , the deathline is over", HttpStatus.BAD_REQUEST);
        }
        if(choixRepository.findByInscriptionAndConcour(choix.getInscription(),concour)!=null) {
            throw new AlreadyExistException();
        }

        if(!choix.getInscription().getNiveau().equals(concour.getNiveau())) {
            throw new CustomException("you can't have this choice as it's for an other type of applications", HttpStatus.BAD_REQUEST);
        }

        if(choixRepository.countChoixByInscription(choix.getInscription())>=3){
            throw new CustomException("you can't have more then three choices", HttpStatus.BAD_REQUEST);
        }

        final Double[] resultPreselection = {0.0};
        choix.getInscription().getDiplomes().forEach(diplome -> {
            resultPreselection[0] += diplome.getNote();
        });

        choix.setConcour(concour);
        Choix choixNew=choixRepository.save(choix);

        String locationAbbreviation = choix.getConcour().getReference().toUpperCase();
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        String generatedName = locationAbbreviation + "-" + formattedDate;
        Result result=resultService.saveResult(Result.builder().notePreselection(resultPreselection[0]/choix.getInscription().getDiplomes().size()).choix(choixNew).resultRef(generatedName).build());
        choixNew.setResult(result);

        return choixRepository.save(choixNew);
    }

    @Override
    public Choix findByInscriptionAndConcours(Inscription inscription, Concour concour){
        Concour concour1=this.findConcour(concour);
        return choixRepository.findByInscriptionAndConcour(inscription,concour1);
    }


    @Override
    public  List<Inscription> PreselectionSeats(Concour c){
        Concour concour=this.findConcour(c);
        return choixRepository.findChoixWithInscriptionByConcourAndPreselectionNbrPlace(concour, PageRequest.of(0, concour.getNbreplaceConcoursEcrit())).stream().map(Choix::getInscription).collect(Collectors.toList());
    }

    @Override
    public  List<Inscription> OralSeats(Concour c){
        Concour concour=this.findConcour(c);
        return choixRepository.findChoixWithInscriptionByConcourAndWritingNbrPlace(concour, PageRequest.of(0, concour.getNbreplaceConcoursOral())).stream().map(Choix::getInscription).collect(Collectors.toList());
    }

    @Override
    public  List<Inscription> FinalSeats(Concour c){
        Concour concour=this.findConcour(c);
        return choixRepository.findChoixWithInscriptionByConcourAndPreselectionNbrPlace(concour, PageRequest.of(0, concour.getNbreplace())).stream().map(Choix::getInscription).collect(Collectors.toList());
    }

    @Override
   public List<Inscription> findByConcours(Concour c){
        Concour concour=this.findConcour(c);
        return  choixRepository.findByConcour(concour).stream().map(Choix::getInscription).collect(Collectors.toList());
   }

   @Override
   public  List<Inscription> PeselectionListByConcours(Concour c){
       Concour concour=this.findConcour(c);
       return choixRepository.findChoixWithInscriptionByConcourAndPreselection(concour).stream().map(Choix::getInscription).collect(Collectors.toList());
   }

   @Override
    public  List<Inscription> WritingListByConcours(Concour c){
       Concour concour=this.findConcour(c);
       return choixRepository.findChoixWithInscriptionByConcourAndWriting(concour).stream().map(Choix::getInscription).collect(Collectors.toList());
    }

    @Override
    public  List<Inscription> AdmisListByConcours(Concour c){
        Concour concour=this.findConcour(c);
        return choixRepository.findChoixWithInscriptionByConcourAndAdmis(concour).stream().map(Choix::getInscription).collect(Collectors.toList());
    }

    @Override
    public  boolean countPeselectionListByConcours(Concour c){
        Concour concour=this.findConcour(c);
        return choixRepository.countChoixWithInscriptionByConcourAndPreselection(concour.getId()) < concour.getNbreplaceConcoursEcrit();

    }

    @Override
    public  Boolean countWritingListByConcours(Concour c){
        Concour concour=this.findConcour(c);
        return choixRepository.countChoixWithInscriptionByConcourAndWriting(concour) < concour.getNbreplaceConcoursOral();

    }

    @Override
    public  Boolean countAdmisListByConcours(Concour c){
        Concour concour=this.findConcour(c);
        return choixRepository.countChoixWithInscriptionByConcourAndAdmis(concour) < concour.getNbreplace();
    }
    @Override
    public void delete(Choix choix) {
        choixRepository.delete(choix);
    }


    public Concour findConcour(Concour c){
        Concour concour=concourService.findByReference(c.getReference());
        if(concour==null){
            throw  new CustomException("there's no exam that match this reference "+c.getReference(), HttpStatus.BAD_REQUEST);
        }
        return concour;
    }


}
