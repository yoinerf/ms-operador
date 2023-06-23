package com.unir.apirest_operaciones.model.customers.requestcustomers;

import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    private String NOMBRES;
    private String APELLIDOS;
    private Integer NUMERODOCUMENTO;
    private List<Rental> RENTALS;
}
