package com.example.filRouge.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoefModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double coef;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Module module ;

}
