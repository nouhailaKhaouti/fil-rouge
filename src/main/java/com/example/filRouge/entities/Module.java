package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module {
    private Integer coefModule;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    @ManyToOne
    private Concour concour;
}
