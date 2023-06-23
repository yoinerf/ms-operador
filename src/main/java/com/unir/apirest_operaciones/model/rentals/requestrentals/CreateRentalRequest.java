package com.unir.apirest_operaciones.model.rentals.requestrentals;

import com.unir.apirest_operaciones.model.customers.customer.Customer;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    @Column(name = "ID_CLIENTE")
    private Integer ID_CLIENTE;

    @Column(name = "ID_PELICULA")
    private String ID_PELICULA;

    @Column(name = "FECHA_INICIO_ALQUILER")
    private Date FECHA_INICIO_ALQUILER;

    @Column(name = "FECHA_FIN_ALQUILER")
    private Date FECHA_FIN_ALQUILER;
}

