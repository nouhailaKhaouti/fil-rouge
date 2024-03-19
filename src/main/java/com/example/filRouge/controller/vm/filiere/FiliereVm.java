package com.example.filRouge.controller.vm.filiere;


import com.example.filRouge.controller.vm.departement.DepartementVm;
import lombok.Data;

@Data
public class FiliereVm {
    private String label;
    private DepartementVm departement;
}
