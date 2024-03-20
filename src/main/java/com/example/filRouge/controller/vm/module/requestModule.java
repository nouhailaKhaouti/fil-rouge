package com.example.filRouge.controller.vm.module;

import com.example.filRouge.controller.vm.concour.concourWithReferenceOnly;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class requestModule {
    @NotNull(message = "the reference can't be null")
    @NotBlank(message = "the reference can't be blank")
    private String reference;
    private concourWithReferenceOnly concour;

    private Integer coef;
}
