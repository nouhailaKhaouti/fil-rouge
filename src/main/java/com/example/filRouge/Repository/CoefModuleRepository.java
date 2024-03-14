package com.example.filRouge.Repository;

import com.example.filRouge.entities.CoefModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoefModuleRepository extends JpaRepository<CoefModule, Long> {

    public List<CoefModule> findByCoef(Double coef);
}
