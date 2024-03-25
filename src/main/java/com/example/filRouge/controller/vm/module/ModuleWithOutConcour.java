package com.example.filRouge.controller.vm.module;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModuleWithOutConcour {
    private String reference;
    @NotNull(message = "the coef can't be null")
    @NotBlank(message = "the coef can't be blank")
    private Integer coefModule;
}
