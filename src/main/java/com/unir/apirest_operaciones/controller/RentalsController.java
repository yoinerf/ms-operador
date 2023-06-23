package com.unir.apirest_operaciones.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;



import com.unir.apirest_operaciones.model.rentals.rental.Rental;
import com.unir.apirest_operaciones.model.rentals.requestrentals.CreateRentalRequest;
import com.unir.apirest_operaciones.service.rentals.IRentalsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RentalsController {
    private final IRentalsService service;


    @GetMapping("/rentals")
    public ResponseEntity<List<Rental>> getRentals(@RequestHeader Map<String, String> headers) {

        System.out.println(headers);
        List<Rental> rentals = service.getRentals();

        if (rentals!= null) {
            return ResponseEntity.ok(rentals);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/rentals/{rentalId}")
    public ResponseEntity<Rental> getRental(@PathVariable String rentalId) {

        //log.info("Request received for product {}", productId);
        Rental rental = service.getRental(rentalId);

        if (rental != null) {
            return ResponseEntity.ok(rental);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/rentals/{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable String rentalId) {

        Boolean removed = service.removeRental(rentalId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/rentals")
    public ResponseEntity<Rental> getRental(@RequestBody CreateRentalRequest request) {

        Rental createdRental = service.createRental(request);

        if (createdRental != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRental);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
