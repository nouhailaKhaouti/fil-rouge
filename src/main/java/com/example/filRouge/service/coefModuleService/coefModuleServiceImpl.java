package com.example.filRouge.service.coefModuleService;

import com.example.filRouge.Repository.CoefModuleRepository;
import com.example.filRouge.entities.CoefModule;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Module;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.exception.NotFoundException;
import com.example.filRouge.service.concourService.concourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class coefModuleServiceImpl implements coefModuleService{
    final private CoefModuleRepository coefModuleRepository;
    final private concourService concourService;

    @Override
    public List<CoefModule> findByCoef(Double coef) {
        return coefModuleRepository.findByCoef(coef);
    }

    @Override
    public CoefModule saveCoefModule(CoefModule coefModule) {
            if(coefModule.getCoef().isNaN()){
                throw new CustomException("the coef your trying to save is empty", HttpStatus.BAD_REQUEST);
            }

        return coefModuleRepository.save(coefModule);
    }

    @Override
    public double totalCoef(Concour concour) {
        concour = concourService.findByReference(concour.getReference());
        double somme = 0;
        for (Module modules : concour.getModules()) {
            somme += modules.getCoefModule().getCoef();
        }
        return somme;
    }

    @Override
    public CoefModule findCoefById(Long id) {

               Optional<CoefModule> module= coefModuleRepository.findById(id);
         if(module.isEmpty()){
             throw new NotFoundException();
         }
         return module.get();
    }

    @Override
    public CoefModule deleteCoef(Long id) {
        return null;
    }
}
