package com.example.filRouge.service.moduleservice;


import com.example.filRouge.Repository.ModuleRepository;
import com.example.filRouge.entities.Concour;
import com.example.filRouge.entities.Module;
import com.example.filRouge.exception.AlreadyExistException;
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
public class moduleServiceImpl implements moduleService{

    final private ModuleRepository moduleRepository;
    @Override
    public List<Module> findByConcourReference(String reference) {
        return moduleRepository.findByConcour(Concour.builder().reference(reference).build());
    }

    @Override
    public Module findByReference(String reference) {
        Optional<Module> module=moduleRepository.findByReference(reference);
        if(module.isEmpty()){
            throw new NotFoundException();
        }
        return module.get();
    }

    @Override
    public Module findById(Long id) {
        Optional<Module> module=moduleRepository.findById(id);
        if(module.isEmpty()){
            throw new NotFoundException();
        }
        return module.get();
    }

    @Override
    public Module saveModule(Module module) {
        Optional<Module> OptionalModule=moduleRepository.findByReference(module.getReference());
        if(OptionalModule.isPresent()){
            throw new AlreadyExistException();
        }
        return moduleRepository.save(module);
    }

    @Override
    public Module updateModule(Module module) {
        Module module1=findByReference(module.getReference());
        module1.setCoefModule(module.getCoefModule());
        return moduleRepository.save(module1);
    }

    @Override
    public void DeleteModule(Module module) {
        moduleRepository.delete(findByReference(module.getReference()));
    }

    @Override
    public Module DeleteModuleById(Long id) {
        return null;
    }

}
