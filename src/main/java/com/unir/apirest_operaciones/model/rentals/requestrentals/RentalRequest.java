package com.unir.apirest_operaciones.model.rentals.requestrentals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalRequest {
    private List<String> Movies ;
}
