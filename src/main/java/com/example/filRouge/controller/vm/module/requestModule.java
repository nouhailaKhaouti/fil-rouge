package com.example.filRouge.controller.vm.module;

import com.example.filRouge.controller.vm.concour.concoursWithReferenceOnly;
import lombok.Data;

@Data
public class requestModule {
    private String reference;
    private concoursWithReferenceOnly concours;
}
