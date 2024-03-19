package com.example.filRouge.controller.vm.concour;

import com.example.filRouge.controller.vm.filiere.filiereLabel;
import com.example.filRouge.controller.vm.module.ModuleWithOutConcour;
import com.example.filRouge.entities.Niveau;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Data
public class requestConcour {
    private List<ModuleWithOutConcour> modules;
    @NotNull(message = "the reference can't be null")
    @NotBlank(message = "the reference can't be blank")
    private String reference;

    @Positive(message = "the number of participants can't be negative")
    @NotNull(message = "the number of Participants can't be null")
    private Integer anneeConcours;

    @NotNull(message = "the date can't be null")
    @Future(message = "the date of the competition can't be in the past")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dateConcoursEcrit;
    @NotNull(message = "the reference can't be null")
    @NotBlank(message = "the reference can't be blank")
    private LocalDate dateConcoursOral;
    @Positive(message = "the number of participants can't be negative")
    @NotNull(message = "the number of Participants can't be null")
    private Integer nbreplace;
    @Positive(message = "the number of participants can't be negative")
    @NotNull(message = "the number of Participants can't be null")
    private Integer nbreplaceConcoursEcrit;
    @Positive(message = "the number of participants can't be negative")
    @NotNull(message = "the number of Participants can't be null")
    private Integer nbreplaceConcoursOral;

    private filiereLabel filiere;
    private Niveau niveau;
}
