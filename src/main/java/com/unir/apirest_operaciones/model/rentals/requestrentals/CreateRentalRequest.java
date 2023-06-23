package com.unir.apirest_operaciones.model.rentals.requestrentals;

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
    private String client;
    private String movie;
    private String FECHA_INICIO_ALQUILER;
    private String FECHA_FIN_ALQUILER;
}

