package com.unir.apirest_operaciones.service.rentals;

import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import com.unir.apirest_operaciones.model.rentals.requestrentals.CreateRentalRequest;

import java.util.List;

public interface IRentalsService {
    List<Rental> getRentals(String client, String movie);

    Rental getRental(String rentalId);

    Boolean removeRental(String rentalId);

    Rental createRental(CreateRentalRequest request);

}
