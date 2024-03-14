package com.example.filRouge.service.coefModuleService;

import com.example.filRouge.entities.CoefModule;
import com.example.filRouge.entities.Concour;
import org.springframework.stereotype.Service;

import java.util.List;

public interface coefModuleService {
    public List<CoefModule> findByCoef(Double coef);

    public CoefModule saveCoefModule(CoefModule coefModule);

    public double totalCoef(Concour concour);

    public CoefModule findCoefById(Long id);

    public CoefModule deleteCoef(Long id);
}
