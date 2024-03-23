package com.example.filRouge.controller.vm.choix;

import com.example.filRouge.controller.vm.concour.concourWithReferenceOnly;
import com.example.filRouge.entities.Result;
import lombok.Data;


@Data
public class respondeChoix {
    private concourWithReferenceOnly concour;

    private Result result;
}
