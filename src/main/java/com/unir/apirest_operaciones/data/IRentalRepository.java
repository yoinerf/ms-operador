package com.unir.apirest_operaciones.data;

import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface IRentalRepository extends ElasticsearchRepository<Rental,String> {

    List<Rental> findByMovie(String name);

    Optional<Rental> findById(String id);

    Rental save(Rental movie);

    void delete(Rental movie);

}
