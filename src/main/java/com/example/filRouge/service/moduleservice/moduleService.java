package com.example.filRouge.service.moduleservice;

import com.example.filRouge.entities.Module;

import java.util.List;

public interface moduleService {
    public List<Module> findByConcourReference(String reference);

    public Module findByReference(String reference);

    public Module findById(Long id);

    public Module saveModule(Module module);

    public Module updateModule(Module moduleConcour);

    public void DeleteModule(Module moduleConcour);

    public Module DeleteModuleById(Long id);

}
