package com.example.filRouge.controller.vm.concour;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class concourWithReferenceOnly {
    @NotNull(message = "the exam reference can't be null")
    @NotBlank(message = "the exam reference can't be blank")
    private String reference;
}
