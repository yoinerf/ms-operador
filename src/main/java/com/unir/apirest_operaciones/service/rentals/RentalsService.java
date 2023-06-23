package com.unir.apirest_operaciones.service.rentals;

import com.unir.apirest_operaciones.data.IRentalRepository;
import com.unir.apirest_operaciones.facade.MoviesFacade;

import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import com.unir.apirest_operaciones.model.rentals.requestrentals.CreateRentalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalsService implements IRentalsService {

    @Autowired
    private IRentalRepository repository;

    @Autowired
    private MoviesFacade moviesFacade;


    @Override
    public List<Rental> getRentals() {
        List<Rental> rentals= repository.findAll();
        return rentals.isEmpty() ? null: rentals;
    }

    @Override
    public Rental getRental(String rentalId) {
        return repository.findById(Integer.valueOf(rentalId)).orElse(null) ;
    }

    @Override
    public Boolean removeRental(String rentalId) {
        Rental rental=repository.findById(Integer.valueOf(rentalId)).orElse(null);

        if (rental != null) {
            repository.delete(rental);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Rental createRental(CreateRentalRequest request) {
        if (request != null && request.getID_CLIENTE()!=null
                && request.getID_PELICULA()!=null
                && request.getFECHA_INICIO_ALQUILER() != null
                && request.getFECHA_FIN_ALQUILER() !=null
            ) {

            Rental rental = Rental.builder()
                              .ID_CLIENTE(request.getID_CLIENTE())
                              .ID_PELICULA(request.getID_PELICULA())
                              .FECHA_INICIO_ALQUILER(request.getFECHA_INICIO_ALQUILER())
                              .FECHA_FIN_ALQUILER(request.getFECHA_FIN_ALQUILER())
                              .build();

            ResponseEntity<String> respuestaPeli = moviesFacade.getMovie(rental.getID_PELICULA());
            if (!respuestaPeli.getStatusCode().equals(HttpStatus.OK)){
                return null;
            }

            return repository.save(rental);
        } else {
            return null;
        }
    }
}
