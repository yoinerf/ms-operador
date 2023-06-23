package com.unir.apirest_operaciones.model.rentals.rental;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "alquiler")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_ALQUILER;

    @Column(name = "ID_CLIENTE")
    private Integer ID_CLIENTE;

    @Column(name = "ID_PELICULA")
    private String ID_PELICULA;

    @Column(name = "FECHA_INICIO_ALQUILER")
    private Date FECHA_INICIO_ALQUILER;

    @Column(name = "FECHA_FIN_ALQUILER")
    private Date FECHA_FIN_ALQUILER;


}
