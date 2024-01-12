package com.example.taskflow.domaine;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Concour {
    @Id
    private String code;

    private String nom_concour;
    private LocalDate date;
    private String filiere;
    private String criteres_evaluation;
    private Double result;

}
