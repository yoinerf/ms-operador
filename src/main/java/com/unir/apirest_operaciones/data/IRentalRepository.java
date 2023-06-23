package com.unir.apirest_operaciones.data;

import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentalRepository extends JpaRepository <Rental,Integer> {

}
