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
    @OneToOne(cascade = CascadeType.REMOVE)
    private CoefModule coefModule;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reference;
    @ManyToOne
    private Concour concour;
}
